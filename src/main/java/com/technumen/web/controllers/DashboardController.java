package com.technumen.web.controllers;

import com.technumen.models.Employee;
import com.technumen.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequestMapping(value = "/dashboard")
public class DashboardController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView forwardToAppropriateDashboard(HttpSession session, Model model) {
        log.info("Inside the forwardToAppropriateDashboard method of DashboardController");
        Employee employee = (Employee) session.getAttribute("user");
        //Check if the employee object exist in the session.
        if (employee == null) {
            log.error("Cannot find employee object in the session, so forwarding to Login page");
            return new ModelAndView("redirect:/login");
        }
        log.info("Check for Employee role and forward to appropriate employee role");
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
        log.info("View Name from the switch case: " + viewName);
        model.addAttribute("currentEndDate", DateUtils.getCurrentTimesheetWeekEndDate());
        return new ModelAndView(viewName);
    }
}
