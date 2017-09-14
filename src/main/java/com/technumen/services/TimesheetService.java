package com.technumen.services;

import com.technumen.models.Timesheet;

import java.util.Date;

public interface TimesheetService {

    /**
     * Save Timesheet information along with file upload information.
     *
     * @param timesheetObj
     */
    public void save(Timesheet timesheetObj) throws Exception;

    /**
     * Returns the Timesheet information from the Timesheet table based on the given timesheetId.
     *
     * @param timesheetId
     * @return
     */
    public Timesheet getTimesheetByTimesheetId(long timesheetId) throws Exception;

    /**
     * Returns the Timesheet information from the Timesheet table based on the given Timesheet EndDate.
     *
     * @param endDate
     * @return
     */
    public Timesheet getTimesheetByEndDate(Date endDate) throws Exception;


}
