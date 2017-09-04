package com.technumen.daos;


import com.technumen.models.Timesheet;

import java.util.Date;

public interface TimesheetDao {
    Timesheet getTimesheetByEmpIdEndDate(long empId, Date timesheetEndDate) throws Exception;
}
