package com.technumen.web.controllers;

import com.technumen.models.Timesheet;
import com.technumen.services.TimesheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class TimesheetController {
    @Autowired
    TimesheetService timesheetService;

    @PostMapping("/addTimesheet")
    public ModelAndView addTimesheet(
            @ModelAttribute("timesheetObj") Timesheet timesheetObj, BindingResult result,
            SessionStatus status, Model model, RedirectAttributes redirectAttributes) {
        log.info("Inside addTimesheet post method.");
        MultipartFile file = timesheetObj.getUploadFile();
        if (file != null) {
            log.info("Name: " + file.getName() + " File type: " + file.getContentType() +
                    "Size: " + file.getSize() + " Original Name: " + file.getOriginalFilename());
        }
        return null;
    }

}
