package com.technumen.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Scope(value = "prototype")
public class EmployeeRoles implements Serializable {

    private long roleId;
    private long departmentId;
    private String roleName;
    private String roleDescription;
    private String roleDisplayName;
    private String jobType;
    private String jobTypeDescription;
    private String jobTypeDisplayName;
    private double eligibleWeeklyHours;
    private double eligibleOvertimeWeeklyHours;
    private double timeoffDaysPerYear;
    private double timeOffDaysPerWeek; //Not required, but just in case...
    private Date dateRoleCreated;
    private boolean roleActive;
    private Date dateRoleInactivated;

    public EmployeeRoles() {
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleDisplayName() {
        return roleDisplayName;
    }

    public void setRoleDisplayName(String roleDisplayName) {
        this.roleDisplayName = roleDisplayName;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobTypeDescription() {
        return jobTypeDescription;
    }

    public void setJobTypeDescription(String jobTypeDescription) {
        this.jobTypeDescription = jobTypeDescription;
    }

    public String getJobTypeDisplayName() {
        return jobTypeDisplayName;
    }

    public void setJobTypeDisplayName(String jobTypeDisplayName) {
        this.jobTypeDisplayName = jobTypeDisplayName;
    }

    public double getEligibleWeeklyHours() {
        return eligibleWeeklyHours;
    }

    public void setEligibleWeeklyHours(double eligibleWeeklyHours) {
        this.eligibleWeeklyHours = eligibleWeeklyHours;
    }

    public double getEligibleOvertimeWeeklyHours() {
        return eligibleOvertimeWeeklyHours;
    }

    public void setEligibleOvertimeWeeklyHours(double eligibleOvertimeWeeklyHours) {
        this.eligibleOvertimeWeeklyHours = eligibleOvertimeWeeklyHours;
    }

    public double getTimeoffDaysPerYear() {
        return timeoffDaysPerYear;
    }

    public void setTimeoffDaysPerYear(double timeoffDaysPerYear) {
        this.timeoffDaysPerYear = timeoffDaysPerYear;
    }

    public double getTimeOffDaysPerWeek() {
        return timeOffDaysPerWeek;
    }

    public void setTimeOffDaysPerWeek(double timeOffDaysPerWeek) {
        this.timeOffDaysPerWeek = timeOffDaysPerWeek;
    }

    public Date getDateRoleCreated() {
        return dateRoleCreated;
    }

    public void setDateRoleCreated(Date dateRoleCreated) {
        this.dateRoleCreated = dateRoleCreated;
    }

    public boolean isRoleActive() {
        return roleActive;
    }

    public void setRoleActive(boolean roleActive) {
        this.roleActive = roleActive;
    }

    public Date getDateRoleInactivated() {
        return dateRoleInactivated;
    }

    public void setDateRoleInactivated(Date dateRoleInactivated) {
        this.dateRoleInactivated = dateRoleInactivated;
    }
}
