package com.technumen.services;

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
    TimesheetDao timesheetDao;
    private Logger logger = LoggerFactory.getLogger(TimesheetDataServiceImpl.class);

    @Override
    public Timesheet getTimesheetByEmpIdEndDate(long empId, Date timesheetEndDate) throws Exception {
        logger.info("Inside getTimesheetByEndDateEmpId method of TimesheetDataServiceImpl---> " +
                "empId: " + empId + " timesheetEndDate: " + timesheetEndDate);
        return timesheetDao.getTimesheetByEmpIdEndDate(empId, timesheetEndDate);
    }
}
