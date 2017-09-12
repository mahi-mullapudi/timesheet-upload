package com.technumen.models;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Scope("prototype")
@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id", unique = true, nullable = false)
    private long employeeId; //Unique Id and the primary key for Employee table.
    @Column(unique = true, nullable = false)
    private String employeeEmailId; //Primary EmailId/User Name of the user.
    @Column(unique = true, nullable = false)
    private String empPassword; //This will be stored as a Hash value.
    @Transient
    private String empPassword2; //Just a confirmation field used for validation. Not saved/updated.
    private String assignedEmployeeId; //TechNumen assigned Identifier.
    @Column(unique = true, nullable = false)
    private String employeeFirstName;
    @Column(unique = true, nullable = false)
    private String employeeLastName;
    @Transient
    private String employeeFullName;
    private String employeeMiddleName;
    private String employeeTitle; //Description of the Employee Title.
    private int employeeRoleId; //Foreign Reference for Employee_Roles table.
    private String employeeRoleDesc;
    private String employeePhone;
    private String employeePhoneExt;
    private String clientName; //Name of the Client.
    private String clientAddress;//Address of the Client.
    private String clientCity;
    private String clientState;
    private String clientZip;
    private long departmentId;//Primary key of Department table.
    private String departmentName;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    private Date employeeStartDate; //Account Created Date.
    private String accountStatusFlag; //Flag to check if the account is Active or Inactive.
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dteInactivated;
    private String nmeUserInactivated; // Who inactivated this user
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dteCreated;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
    private Set<Timesheet> timesheetRecords = new HashSet<>();

    protected Employee() {
    }

    public Employee(String employeeEmailId, String empPassword, String empPassword2, String assignedEmployeeId, String employeeFirstName, String employeeLastName, String employeeFullName, String employeeMiddleName, String employeeTitle, int employeeRoleId, String employeeRoleDesc, String employeePhone, String employeePhoneExt, String clientName, String clientAddress, String clientCity, String clientState, String clientZip, long departmentId, String departmentName, Date employeeStartDate, String accountStatusFlag, Date dteInactivated, String nmeUserInactivated, Date dteCreated, Set<Timesheet> timesheetRecords) {
        this.employeeEmailId = employeeEmailId;
        this.empPassword = empPassword;
        this.empPassword2 = empPassword2;
        this.assignedEmployeeId = assignedEmployeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeFullName = employeeFullName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeTitle = employeeTitle;
        this.employeeRoleId = employeeRoleId;
        this.employeeRoleDesc = employeeRoleDesc;
        this.employeePhone = employeePhone;
        this.employeePhoneExt = employeePhoneExt;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientCity = clientCity;
        this.clientState = clientState;
        this.clientZip = clientZip;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeStartDate = employeeStartDate;
        this.accountStatusFlag = accountStatusFlag;
        this.dteInactivated = dteInactivated;
        this.nmeUserInactivated = nmeUserInactivated;
        this.dteCreated = dteCreated;
        this.timesheetRecords = timesheetRecords;
    }

    public String getEmployeeFullName() {
        return (StringUtils.isNotBlank(this.employeeFirstName) ? this.employeeFirstName : "") +
                " " + (StringUtils.isNotBlank(this.employeeLastName) ? this.employeeLastName : "");
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeEmailId() {
        return employeeEmailId;
    }

    public void setEmployeeEmailId(String employeeEmailId) {
        this.employeeEmailId = employeeEmailId;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public String getEmpPassword2() {
        return empPassword2;
    }

    public void setEmpPassword2(String empPassword2) {
        this.empPassword2 = empPassword2;
    }

    public String getAssignedEmployeeId() {
        return assignedEmployeeId;
    }

    public void setAssignedEmployeeId(String assignedEmployeeId) {
        this.assignedEmployeeId = assignedEmployeeId;
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

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeTitle() {
        return employeeTitle;
    }

    public void setEmployeeTitle(String employeeTitle) {
        this.employeeTitle = employeeTitle;
    }

    public int getEmployeeRoleId() {
        return employeeRoleId;
    }

    public void setEmployeeRoleId(int employeeRoleId) {
        this.employeeRoleId = employeeRoleId;
    }

    public String getEmployeeRoleDesc() {
        return employeeRoleDesc;
    }

    public void setEmployeeRoleDesc(String employeeRoleDesc) {
        this.employeeRoleDesc = employeeRoleDesc;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeePhoneExt() {
        return employeePhoneExt;
    }

    public void setEmployeePhoneExt(String employeePhoneExt) {
        this.employeePhoneExt = employeePhoneExt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public String getClientZip() {
        return clientZip;
    }

    public void setClientZip(String clientZip) {
        this.clientZip = clientZip;
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

    public Date getEmployeeStartDate() {
        return employeeStartDate;
    }

    public void setEmployeeStartDate(Date employeeStartDate) {
        this.employeeStartDate = employeeStartDate;
    }

    public String getAccountStatusFlag() {
        return accountStatusFlag;
    }

    public void setAccountStatusFlag(String accountStatusFlag) {
        this.accountStatusFlag = accountStatusFlag;
    }

    public Date getDteInactivated() {
        return dteInactivated;
    }

    public void setDteInactivated(Date dteInactivated) {
        this.dteInactivated = dteInactivated;
    }

    public String getNmeUserInactivated() {
        return nmeUserInactivated;
    }

    public void setNmeUserInactivated(String nmeUserInactivated) {
        this.nmeUserInactivated = nmeUserInactivated;
    }

    public Date getDteCreated() {
        return dteCreated;
    }

    public void setDteCreated(Date dteCreated) {
        this.dteCreated = dteCreated;
    }

    public Set<Timesheet> getTimesheetRecords() {
        return timesheetRecords;
    }

    public void setTimesheetRecords(Set<Timesheet> timesheetRecords) {
        this.timesheetRecords = timesheetRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        return getEmployeeId() == employee.getEmployeeId();
    }

    @Override
    public int hashCode() {
        return (int) (getEmployeeId() ^ (getEmployeeId() >>> 32));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeEmailId='" + employeeEmailId + '\'' +
                ", assignedEmployeeId='" + assignedEmployeeId + '\'' +
                ", employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", employeeFullName='" + employeeFullName + '\'' +
                ", employeeMiddleName='" + employeeMiddleName + '\'' +
                ", employeeTitle='" + employeeTitle + '\'' +
                ", employeeRoleId=" + employeeRoleId +
                ", employeeRoleDesc='" + employeeRoleDesc + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                ", employeePhoneExt='" + employeePhoneExt + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientAddress='" + clientAddress + '\'' +
                ", clientCity='" + clientCity + '\'' +
                ", clientState='" + clientState + '\'' +
                ", clientZip='" + clientZip + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", employeeStartDate=" + employeeStartDate +
                ", accountStatusFlag='" + accountStatusFlag + '\'' +
                ", dteInactivated=" + dteInactivated +
                ", nmeUserInactivated='" + nmeUserInactivated + '\'' +
                ", dteCreated=" + dteCreated +
                '}';
    }
}
