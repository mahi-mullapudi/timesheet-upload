package com.technumen.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class RegistrationController {

    @GetMapping("registration")
    public ModelAndView getRegistration() {

        return new ModelAndView("registration");
    }
}

