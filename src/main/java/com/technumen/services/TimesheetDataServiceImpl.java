package com.technumen.services;

import com.technumen.daos.EmployeeDao;
import com.technumen.daos.TimesheetDao;
import com.technumen.models.Employee;
import com.technumen.models.Timesheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TimesheetDataServiceImpl implements TimesheetDataService {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    TimesheetDao timesheetDao;
    private Logger logger = LoggerFactory.getLogger(TimesheetDataServiceImpl.class);

    @Override
    public Employee getEmployeeById(long empId) throws Exception {
        logger.info("Inside getEmployeeById method of TimesheetDataServiceImpl---> empId: " + empId);
        return employeeDao.getEmployeeByEmpId(empId);
    }


    @Override
    public Timesheet getTimesheetByEmpIdEndDate(long empId, Date timesheetEndDate) throws Exception {
        logger.info("Inside getTimesheetByEndDateEmpId method of TimesheetDataServiceImpl---> " +
                "empId: " + empId + " timesheetEndDate: " + timesheetEndDate);
        return timesheetDao.getTimesheetByEmpIdEndDate(empId, timesheetEndDate);
    }
}
