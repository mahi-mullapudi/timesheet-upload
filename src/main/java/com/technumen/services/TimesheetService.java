package com.technumen.services;

import com.technumen.models.Timesheet;

public interface TimesheetService {

    /**
     * Returns the Timesheet information from the Timesheet table based on the given timesheetId.
     *
     * @param timesheetId
     * @return
     */
    public Timesheet getTimesheetByTimesheetId(long timesheetId);
}
