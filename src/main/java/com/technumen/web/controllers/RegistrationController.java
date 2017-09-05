package com.technumen.web.controllers;

import com.technumen.models.Employee;
import com.technumen.web.validators.RegistrationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/registration")
@Slf4j
public class RegistrationController {

    @Autowired
    Employee employee;
    //Autowiring Validators
    @Autowired
    RegistrationValidator registrationValidator;

    @GetMapping
    public ModelAndView getRegistration() {
        employee = new Employee();
        //Initiating ModelAndView object with the Employee object
        return new ModelAndView("registration", "employee", employee);
    }

    @PostMapping
    public ModelAndView submitRegistation(@ModelAttribute("employee") Employee employeeRegistration, BindingResult result,
                                          SessionStatus status, Model model, RedirectAttributes redirectAttributes) {
        registrationValidator.validate(employeeRegistration, result);
        if (result.hasErrors()) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Invalid / Missing Information. Please correct the information entered below!!");
            return new ModelAndView("registration", "employee", employeeRegistration);
        }

        return new ModelAndView("registration", "employee", employee);
    }
}

