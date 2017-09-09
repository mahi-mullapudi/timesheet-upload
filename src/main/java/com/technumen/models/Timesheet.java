package com.technumen.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Maintains Timesheet Information.
 */
@Component
@Scope("prototype")
@Data
@Entity
public class Timesheet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TIMESHEET_ID", unique = true, nullable = false)
    private long timesheetId;
    private String timesheetType;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    private String timesheetStatus;
    //Timesheet Information
    private double regularHours;
    private double extraHours;
    //Upload Information
    private java.sql.Blob blobMessage;
    private String dscFileName;
    @Transient
    private transient String rowid;
    private long fileSize;
    private String dscComments;
    //Audit Information
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateLastModified;
    private String nameCreated; //Name of the user Uploaded the timesheet.
    private String nameLastModified;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", nullable = false)
    private Employee employee;
}
