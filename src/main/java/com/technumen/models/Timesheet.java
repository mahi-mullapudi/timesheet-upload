package com.technumen.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * Maintains information of the Timesheet.
 */
@Component
@Scope("prototype")
@Data
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

}
