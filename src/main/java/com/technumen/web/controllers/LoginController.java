package com.technumen.web.controllers;

import com.technumen.exceptions.CustomException;
import com.technumen.models.Employee;
import com.technumen.models.Login;
import com.technumen.services.AuthenticationService;
import com.technumen.utils.EncryptDecryptUtils;
import com.technumen.web.validators.LoginValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {

    @Autowired
    Login loginObj;
    @Autowired
    LoginValidator loginValidator;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    EncryptDecryptUtils encryptDecryptUtils;

    @GetMapping
    public ModelAndView login(HttpServletRequest request) {
        log.info("Inside login method:: ");
        String ipAddress = request.getRemoteAddr();
        log.info("ipAddress from request: " + ipAddress);
        loginObj = new Login();
        loginObj.setClientIpAddress(ipAddress);
        return new ModelAndView("login", "loginObj", loginObj);
    }

    @GetMapping(params = "encodedEmail")
    public ModelAndView redirectLogin(@RequestParam(value = "encodedEmail", required = true) String encodedEmail, HttpServletRequest request) {
        log.info("Inside redirectLogin method:: encodedEmail: " + encodedEmail);
        loginObj = new Login();
        loginObj.setClientIpAddress(request.getRemoteAddr());
        loginObj.setEmailId(encryptDecryptUtils.decodeInputString(encodedEmail));

        return new ModelAndView("login", "loginObj", loginObj);
    }

    @PostMapping()
    public ModelAndView submitLogin(HttpServletRequest request,
                                    @ModelAttribute("loginObj") Login loginObj, BindingResult result)
            throws CustomException {
        log.info("Inside submitLogin method:: ");
        Employee employee;
        loginValidator.validate(loginObj, result);
        if (result.hasErrors()) {
            log.info("Inside result errors loop. Redirecting it to login page.");
            return new ModelAndView("login");
        }
        log.info("No errors. Login authentication successful!!");
        try {
            employee = authenticationService.getEmployeeByEmailId(loginObj.getEmailId());
        } catch (Exception ex) {
            log.error("Exception while getting the employee object:: " + ex);
            throw new CustomException("TODO error code", ex);
        }

        HttpSession existingSession = request.getSession(false);
        if (existingSession != null) {
            log.info("Invalidating the existing session...");
            existingSession.invalidate();
        }
        log.debug("\n Creating a new session");
        HttpSession newSession = request.getSession(true);
        String viewName = "";
        switch (employee.getEmployeeRoleId()) {
            //Employee
            case 100:
                employee.setEmployeeRoleDesc("employee");
                viewName = "employee/dashboard";
                break;
            //Employer-Staff
            case 200:
                employee.setEmployeeRoleDesc("staff");
                viewName = "staff/dashboard-staff";
                break;
            //Admin
            case 500:
                employee.setEmployeeRoleDesc("admin");
                viewName = "admin/dashboard-admin";
                break;
            default:
                viewName = "login";
        }

        //Setting user object in the session.
        newSession.setAttribute("user", employee);

        return new ModelAndView(viewName);
    }

}

