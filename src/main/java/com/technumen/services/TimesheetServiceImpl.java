package com.technumen.services;

import com.technumen.models.Timesheet;
import com.technumen.repositories.TimesheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TimesheetServiceImpl implements TimesheetService {

    @Autowired
    TimesheetRepository timesheetRepository;

    @Override
    public void save(Timesheet timesheetObj) throws Exception {
        log.info("Inside save method of TimesheetServiceImpl :: " + timesheetObj.getTimesheetId());
        timesheetRepository.save(timesheetObj);
    }

    @Override
    public Timesheet getTimesheetByTimesheetId(long timesheetId) throws Exception {
        log.info("Inside getTimesheetByTimesheetId method of TimesheetServiceImpl --> timesheetId: " + timesheetId);
        return timesheetRepository.findOne(timesheetId);
    }

    @Override
    public Timesheet getTimesheetByEndDate(Date endDate) throws Exception {
        log.info("Inside getTimesheetByEndDate method of TimesheetServiceImpl :: " + endDate);
        return timesheetRepository.findTimesheetByToDate(endDate);
    }
}
