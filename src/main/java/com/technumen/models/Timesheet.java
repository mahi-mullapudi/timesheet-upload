package com.technumen.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component
@Scope("prototype")
public class Timesheet implements Serializable {

    private long timesheetId;
    private Date timesheetStartDate;
    private Date timesheetEndDate;
    private long employeeId;
    private String comments;
    private String nameLastModified;
    private Date dateLastModified;
    private String nameCreated;
    private Date dateCreated;
    private String timesheetStatus;


    public Timesheet() {
    }

    public Timesheet(long timesheetId, Date timesheetStartDate, Date timesheetEndDate, long employeeId, String comments,
                     String nameLastModified, Date dateLastModified, String nameCreated, Date dateCreated,
                     String timesheetStatus, List<TimesheetDetails> timesheetDetailsList) {
        this.timesheetId = timesheetId;
        this.timesheetStartDate = timesheetStartDate;
        this.timesheetEndDate = timesheetEndDate;
        this.employeeId = employeeId;
        this.comments = comments;
        this.nameLastModified = nameLastModified;
        this.dateLastModified = dateLastModified;
        this.nameCreated = nameCreated;
        this.dateCreated = dateCreated;
        this.timesheetStatus = timesheetStatus;

    }

    public long getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(long timesheetId) {
        this.timesheetId = timesheetId;
    }

    public Date getTimesheetStartDate() {
        return timesheetStartDate;
    }

    public void setTimesheetStartDate(Date timesheetStartDate) {
        this.timesheetStartDate = timesheetStartDate;
    }

    public Date getTimesheetEndDate() {
        return timesheetEndDate;
    }

    public void setTimesheetEndDate(Date timesheetEndDate) {
        this.timesheetEndDate = timesheetEndDate;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNameLastModified() {
        return nameLastModified;
    }

    public void setNameLastModified(String nameLastModified) {
        this.nameLastModified = nameLastModified;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public String getNameCreated() {
        return nameCreated;
    }

    public void setNameCreated(String nameCreated) {
        this.nameCreated = nameCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTimesheetStatus() {
        return timesheetStatus;
    }

    public void setTimesheetStatus(String timesheetStatus) {
        this.timesheetStatus = timesheetStatus;
    }


}
