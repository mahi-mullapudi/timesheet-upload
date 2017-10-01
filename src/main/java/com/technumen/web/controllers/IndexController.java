package com.technumen.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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

    @GetMapping("/reports")
    public String reports(Model model) {
        log.info("Inside reports method of IndexController");
        return "employee/reports";
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("Inside logout method of Index Controller.");
        if (session != null) {
            session.invalidate();
        }
        redirectAttributes.addFlashAttribute("msg", "Logged out successfully !!");
        redirectAttributes.addFlashAttribute("css", "success");

        return new ModelAndView("redirect:/login");
    }
}