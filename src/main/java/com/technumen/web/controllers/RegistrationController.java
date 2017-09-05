package com.technumen.web.controllers;

import com.technumen.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class RegistrationController {

    @Autowired
    Employee employee;

    @GetMapping("registration")
    public ModelAndView getRegistration() {
        employee = new Employee();
        //Creating a ModelAndView object by initiating the object with the Employee object
        return new ModelAndView("registration", "employee", employee);
    }
}

