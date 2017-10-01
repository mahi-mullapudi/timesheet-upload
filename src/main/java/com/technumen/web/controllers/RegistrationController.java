package com.technumen.web.controllers;

import com.technumen.constants.TimesheetConstants;
import com.technumen.exceptions.CustomException;
import com.technumen.models.Employee;
import com.technumen.models.ResetPassword;
import com.technumen.services.AuthenticationService;
import com.technumen.services.EmailService;
import com.technumen.services.RegistrationService;
import com.technumen.utils.AuthenticationUtils;
import com.technumen.utils.EncryptDecryptUtils;
import com.technumen.web.validators.RegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    RegistrationValidator registrationValidator;
    @Autowired
    RegistrationService registrationService;
    @Autowired
    AuthenticationService authService;
    @Autowired
    EmailService emailService;
    @Autowired
    EncryptDecryptUtils encryptDecryptUtils;
    @Autowired
    AuthenticationUtils authUtils;

    @GetMapping("/registration")
    public ModelAndView getRegistration() {
        log.info("Inside getRegistration method of Registration Controller.");
        Employee employeeRegistration = new Employee();
        employeeRegistration.setEmployeeRoleId(100);
        employeeRegistration.setEmployeeRoleDesc("EMPLOYEE");
        //Initiating ModelAndView object with the Employee object
        return new ModelAndView("registration", "employee", employeeRegistration);
    }

    @GetMapping("/staffRegistration")
    public ModelAndView getStaffRegistration(ModelMap modelMap) {
        log.info("Inside getStaffRegistration method of Registration Controller.");
        Map<Integer, String> roleIdDescMap = new TreeMap<>();
        roleIdDescMap.put(TimesheetConstants.USER_ROLE_SUPERVISOR_ID, TimesheetConstants.USER_ROLE_SUPERVISOR);
        roleIdDescMap.put(TimesheetConstants.USER_ROLE_ADMIN_ID, TimesheetConstants.USER_ROLE_ADMIN);

        modelMap.addAttribute("roleIdDescMap", roleIdDescMap);
        modelMap.addAttribute("employee", new Employee());
        //Initiating ModelAndView object with the Employee object
        return new ModelAndView("registration-staff", modelMap);
    }

    @PostMapping("/registration")
    public ModelAndView submitRegistration(@ModelAttribute("employee") Employee employeeRegistration, BindingResult result,
                                           SessionStatus status, Model model, RedirectAttributes redirectAttributes) throws CustomException {
        log.info("Inside submitRegistation method of Registration Controller.");
        registrationValidator.validate(employeeRegistration, result);
        if (result.hasErrors()) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Invalid / Missing Information. Please correct the information entered below!!");
            return new ModelAndView("registration", "employee", employeeRegistration);
        }

        log.info("The form has no errors, so persisting the data.");
        try {
            employeeRegistration.setAccountStatusFlag(TimesheetConstants.REGISTRATION_STATUS_ACTIVE);
            employeeRegistration.setDateCreated(new Date());
            employeeRegistration.setNameUserCreated(employeeRegistration.getEmployeeFullName());
            log.info("Saving the registration details of the Employee.");
            registrationService.saveRegistrationDetails(employeeRegistration);
        } catch (Exception ex) {
            log.error("Exception while saving Registration details: " + ex);
            throw new CustomException("employee.registration.save.error", "Error while saving employee registration details. ");
        }
        log.info("Successfully Registered the user, forwarding to the Login page.");
        redirectAttributes.addFlashAttribute("msg", "You are successfully registered. Please Login to Continue.");
        redirectAttributes.addFlashAttribute("css", "success");

        return new ModelAndView("redirect:/login?encodedEmail=" + encryptDecryptUtils.encodeInputString(employeeRegistration.getEmployeeEmailId()));
    }

    @GetMapping("/forgotPassword")
    public ModelAndView forgotPassword() {
        log.info("Inside forgotPassword method of Registration Controller.");
        return new ModelAndView("forgotPassword", "resetPassword", new ResetPassword());
    }

    @PostMapping("/forgotPassword")
    public ModelAndView submitForgotPassword(@ModelAttribute("resetPassword") ResetPassword resetPassword, BindingResult result,
                                             SessionStatus status, Model model, Errors errors, RedirectAttributes redirectAttributes) {
        log.info("Inside submitForgotPassword method of Registration Controller.");
        try {
            if (!authService.checkEmailIdExists(resetPassword.getEmailId())) {
                log.info("EmailId not found in the system: ");
                errors.rejectValue("emailId", "NotValid.login.email");
            }
        } catch (Exception ex) {
            log.error("Exception while checking for the EmailId: " + ex);
            errors.rejectValue("emailId", "Error.login.email");
        }
        if (result.hasErrors()) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Invalid / Missing Information. Please correct the information entered below!!");
            return new ModelAndView("forgotPassword");
        }
        //Create a alpha numeric password
        String tempPassword = RandomStringUtils.randomAlphanumeric(8);
        log.info("After getting the tempPassword::");
        try {
            int numRows = registrationService.updatePassword(tempPassword, resetPassword.getEmailId());
            log.info("Number of rows affected: " + numRows);
        } catch (Exception ex) {
            log.error("Exception while saving the new random password: " + ex);
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Technical issue while resetting the password. Please contact Admin for more details!!");
            return new ModelAndView("forgotPassword");
        }
        //Send an Email with the temporary password.
        emailService.sendPlainTextMailWithoutAttachment(TimesheetConstants.fromAddress, resetPassword.getEmailId(),
                "",
                "TechNumen Inc., Timesheet Application - Password Reset",
                "We got a request to Reset your password. Below is the temporary password: /n "
                        + tempPassword + ". Please use this temporary password to Login to your account and then " +
                        "you can change your password from the Preferences section.");

        model.addAttribute("resetEmail", resetPassword.getEmailId());
        model.addAttribute("resetConfirmationModal", "yes");

        return new ModelAndView("forgotPassword");
    }

}

