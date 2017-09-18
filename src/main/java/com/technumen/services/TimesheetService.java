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

    /**
     * Approve the Timesheet based on the given TimesheetId.
     *
     * @param timesheetId
     * @throws Exception
     */
    //public void approveTimesheet(long timesheetId) throws Exception;

    /**
     * Reject the Timesheet based on the given TimesheetId and submit the reviewer comments.
     *
     * @param timesheetId
     * @throws Exception
     */
    //public void rejectTimesheet(long timesheetId, String reviewerComments) throws Exception;

}
