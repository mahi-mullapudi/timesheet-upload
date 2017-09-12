package com.technumen.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

/**
 * Maintains Timesheet Information.
 */
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

    protected Timesheet() {
    }

    public Timesheet(String timesheetType, Date fromDate, Date toDate, String timesheetStatus, double regularHours, double extraHours, Blob blobMessage, String dscFileName, String rowid, long fileSize, String dscComments, Date dateCreated, Date dateLastModified, String nameCreated, String nameLastModified, Employee employee) {
        this.timesheetType = timesheetType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.timesheetStatus = timesheetStatus;
        this.regularHours = regularHours;
        this.extraHours = extraHours;
        this.blobMessage = blobMessage;
        this.dscFileName = dscFileName;
        this.rowid = rowid;
        this.fileSize = fileSize;
        this.dscComments = dscComments;
        this.dateCreated = dateCreated;
        this.dateLastModified = dateLastModified;
        this.nameCreated = nameCreated;
        this.nameLastModified = nameLastModified;
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timesheet)) return false;

        Timesheet timesheet = (Timesheet) o;

        return getTimesheetId() == timesheet.getTimesheetId();
    }

    @Override
    public int hashCode() {
        return (int) (getTimesheetId() ^ (getTimesheetId() >>> 32));
    }

    public long getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(long timesheetId) {
        this.timesheetId = timesheetId;
    }

    public String getTimesheetType() {
        return timesheetType;
    }

    public void setTimesheetType(String timesheetType) {
        this.timesheetType = timesheetType;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getTimesheetStatus() {
        return timesheetStatus;
    }

    public void setTimesheetStatus(String timesheetStatus) {
        this.timesheetStatus = timesheetStatus;
    }

    public double getRegularHours() {
        return regularHours;
    }

    public void setRegularHours(double regularHours) {
        this.regularHours = regularHours;
    }

    public double getExtraHours() {
        return extraHours;
    }

    public void setExtraHours(double extraHours) {
        this.extraHours = extraHours;
    }

    public Blob getBlobMessage() {
        return blobMessage;
    }

    public void setBlobMessage(Blob blobMessage) {
        this.blobMessage = blobMessage;
    }

    public String getDscFileName() {
        return dscFileName;
    }

    public void setDscFileName(String dscFileName) {
        this.dscFileName = dscFileName;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getDscComments() {
        return dscComments;
    }

    public void setDscComments(String dscComments) {
        this.dscComments = dscComments;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public String getNameLastModified() {
        return nameLastModified;
    }

    public void setNameLastModified(String nameLastModified) {
        this.nameLastModified = nameLastModified;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Timesheet{" +
                "timesheetId=" + timesheetId +
                ", timesheetType='" + timesheetType + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", timesheetStatus='" + timesheetStatus + '\'' +
                ", regularHours=" + regularHours +
                ", extraHours=" + extraHours +
                ", blobMessage=" + blobMessage +
                ", dscFileName='" + dscFileName + '\'' +
                ", rowid='" + rowid + '\'' +
                ", fileSize=" + fileSize +
                ", dscComments='" + dscComments + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                ", nameCreated='" + nameCreated + '\'' +
                ", nameLastModified='" + nameLastModified + '\'' +
                ", employee=" + employee +
                '}';
    }
}
