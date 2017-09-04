package com.technumen.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/*
    This class maintains the Activity of time sheet for audit purposes.
 */
@Component
@Scope(value = "prototype")
public class TimesheetActivity implements Serializable {

    private long timesheetActivityId;
    private long timesheetId;
    private Date dateLastModified;
    private long empLastModified;
    private String prevStatus; //This refers to the status of the time sheet before modification.
    private String modifiedStatus; //This refers to the status of the time sheet before modification.

    public TimesheetActivity() {
    }

    public long getTimesheetActivityId() {
        return timesheetActivityId;
    }

    public void setTimesheetActivityId(long timesheetActivityId) {
        this.timesheetActivityId = timesheetActivityId;
    }

    public long getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(long timesheetId) {
        this.timesheetId = timesheetId;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public long getEmpLastModified() {
        return empLastModified;
    }

    public void setEmpLastModified(long empLastModified) {
        this.empLastModified = empLastModified;
    }

    public String getPrevStatus() {
        return prevStatus;
    }

    public void setPrevStatus(String prevStatus) {
        this.prevStatus = prevStatus;
    }

    public String getModifiedStatus() {
        return modifiedStatus;
    }

    public void setModifiedStatus(String modifiedStatus) {
        this.modifiedStatus = modifiedStatus;
    }
}
