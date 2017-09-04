package com.technumen.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Component
@Scope("prototype")
public class Employee implements Serializable {

    private long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeMiddleName;
    private String empTitle; //This is the description of the employee role
    private long employeeRoleId; //This maintains the Id for the employee role
    private long departmentId;
    private String departmentName;
    private long reportingManagerId;
    private String reportingManagerName;
    private long altReportingManagerId;
    private String altReportingManagerName;
    private Date startDate;
    private boolean activeFlag;
    private Date inactivatedDate;
    private String inactivatedBy; // This contains the user info. on who inactivated this user

    public Employee() {
    }

    public Employee(long employeeId, String employeeFirstName, String employeeLastName, String employeeMiddleName, String empTitle,
                    long employeeRoleId, long departmentId, String departmentName, long reportingManagerId,
                    String reportingManagerName, long altReportingManagerId, String altReportingManagerName,
                    Date workStartDate, boolean activeFlag, Date inactivatedDate, String inactivatedBy) {

        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeMiddleName = employeeMiddleName;
        this.empTitle = empTitle;
        this.employeeRoleId = employeeRoleId;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.reportingManagerId = reportingManagerId;
        this.reportingManagerName = reportingManagerName;
        this.altReportingManagerId = altReportingManagerId;
        this.altReportingManagerName = altReportingManagerName;
        this.startDate = workStartDate;
        this.activeFlag = activeFlag;
        this.inactivatedDate = inactivatedDate;
        this.inactivatedBy = inactivatedBy;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeFullName() {
        return ((StringUtils.isEmpty(employeeFirstName) ? "" : employeeFirstName)
                + " "
                + (StringUtils.isEmpty(employeeLastName) ? "" : employeeLastName));
    }

    public String getEmpTitle() {
        return empTitle;
    }

    public void setEmpTitle(String empTitle) {
        this.empTitle = empTitle;
    }

    public long getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setEmployeeRoleId(long employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getReportingManagerId() {
        return reportingManagerId;
    }

    public void setReportingManagerId(long reportingManagerId) {
        this.reportingManagerId = reportingManagerId;
    }

    public String getReportingManagerName() {
        return reportingManagerName;
    }

    public void setReportingManagerName(String reportingManagerName) {
        this.reportingManagerName = reportingManagerName;
    }

    public long getAltReportingManagerId() {
        return altReportingManagerId;
    }

    public void setAltReportingManagerId(long altReportingManagerId) {
        this.altReportingManagerId = altReportingManagerId;
    }

    public String getAltReportingManagerName() {
        return altReportingManagerName;
    }

    public void setAltReportingManagerName(String altReportingManagerName) {
        this.altReportingManagerName = altReportingManagerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Date getInactivatedDate() {
        return inactivatedDate;
    }

    public void setInactivatedDate(Date inactivatedDate) {
        this.inactivatedDate = inactivatedDate;
    }

    public String getInactivatedBy() {
        return inactivatedBy;
    }

    public void setInactivatedBy(String inactivatedBy) {
        this.inactivatedBy = inactivatedBy;
    }
}
