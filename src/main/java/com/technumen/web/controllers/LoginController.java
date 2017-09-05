package com.technumen.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
*   Maintains methods for Login functionality.
*   Authenticates users based on UserId - Email Address and Password.
 */
@Controller
@RequestMapping(value = "/login")
@Slf4j
public class LoginController {
    @GetMapping()
    public ModelAndView login() {
        log.info("Inside login method:: ");
        return new ModelAndView("login");
    }

    @PostMapping()
    public ModelAndView submitLogin() {
        log.info("Inside submitLogin method:: ");
        return new ModelAndView("login");
    }

}

