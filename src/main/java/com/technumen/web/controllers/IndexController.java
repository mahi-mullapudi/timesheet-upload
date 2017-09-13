package com.technumen.web.controllers;

import com.technumen.models.Timesheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/addTimesheet")
    public ModelAndView addTimesheet(Model model) {
        log.info("Inside addTimesheet method of IndexController");
        return new ModelAndView("employee/addTimesheet", "timesheetObj", new Timesheet());
    }

}