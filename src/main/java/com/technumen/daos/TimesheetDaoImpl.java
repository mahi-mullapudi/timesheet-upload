package com.technumen.daos;

import com.technumen.models.Timesheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Slf4j
public class TimesheetDaoImpl implements TimesheetDao {

    @Autowired
    Timesheet timesheetObj;

    @Override
    public Timesheet getTimesheetByEmpIdEndDate(long empId, Date timesheetEndDate) throws Exception {
        log.info("Inside getTimesheetByEndDateEmpId method of TimesheetDaoImpl---> " +
                "empId: " + empId + " timesheetEndDate: " + timesheetEndDate);

        return timesheetObj;

    }

}
