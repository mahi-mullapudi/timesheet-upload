package com.technumen.web.controllers;

import com.technumen.constants.TimesheetConstants;
import com.technumen.models.Employee;
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

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
@Slf4j
public class TimesheetController {

    @Autowired
    TimesheetService timesheetService;

    /**
     * Post method to save the timesheet after performing required validations.
     *
     * @param timesheetObj
     * @param result
     * @param status
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/addTimesheet")
    public ModelAndView addTimesheet(
            @ModelAttribute("timesheetObj") Timesheet timesheetObj, BindingResult result,
            SessionStatus status, Model model, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
        log.info("Inside addTimesheet post method.");
        Employee employee = (Employee) session.getAttribute("user");
        MultipartFile file = timesheetObj.getUploadFile();
        if (file != null) {
            log.info("Name: " + file.getName() + " File type: " + file.getContentType() +
                    " Size: " + file.getSize() + " Original Name: " + file.getOriginalFilename());
            timesheetObj.setToDate(new Date());
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.DAY_OF_MONTH, -7);
            Date sevenDaysAgo = cal.getTime();
            timesheetObj.setFromDate(sevenDaysAgo);
            //Upload information
            timesheetObj.setBlobContent(file.getBytes());
            timesheetObj.setFileSize(file.getSize());
            timesheetObj.setDscFileName(file.getOriginalFilename());
            timesheetObj.setTimesheetStatus(TimesheetConstants.TIMESHEET_STATUS_SUBMITTED);
            timesheetObj.setTimesheetType(TimesheetConstants.TIMESHEET_TYPE_WEEKLY);
            timesheetObj.setNameCreated(employee.getEmployeeFullName());
            timesheetObj.setEmployee(employee);
            timesheetObj.setDateCreated(new Date());
            timesheetService.save(timesheetObj);

            redirectAttributes.addAttribute("msg", "Timesheet Added Successfully!!");
            return new ModelAndView("employee/addTimesheet", "timesheetObj", timesheetObj);
        } else {
            log.info("File not found");
            return new ModelAndView("employee/addTimesheet", "timesheetObj", timesheetObj);
        }


    }

}
