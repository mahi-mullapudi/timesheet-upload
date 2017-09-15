package com.technumen.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

/*
*   Maintains methods for basic functions.
 */
@Controller
@Slf4j
public class IndexController {

    @GetMapping("/welcome")
    public String Welcome(Model model) {
        log.info("Inside welcome method of IndexController");
        model.addAttribute("time", new Date());
        model.addAttribute("message", "Hello Mahidhar");
        return "welcome";
    }
}