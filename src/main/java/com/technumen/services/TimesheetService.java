package com.technumen.services;

import com.technumen.models.Timesheet;

import java.util.Date;
import java.util.List;

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
     * Returns the list of Timesheets for a given From Date, To Date and Timesheet Status.
     *
     * @param fromDate
     * @param toDate
     * @param timesheetStatus
     * @return
     * @throws Exception
     */
    public List<Timesheet> getTimesheetSummaryStaff(Date fromDate, Date toDate, String timesheetStatus) throws Exception;

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
