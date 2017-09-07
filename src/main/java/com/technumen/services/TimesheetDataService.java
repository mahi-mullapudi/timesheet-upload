package com.technumen.services;

import com.technumen.models.Employee;
import com.technumen.models.Timesheet;

import java.util.Date;

public interface TimesheetDataService {

    Employee getEmployeeById(long empId) throws Exception;

    Timesheet getTimesheetByEmpIdEndDate(long empId, Date timesheetEndDate) throws Exception;

}
