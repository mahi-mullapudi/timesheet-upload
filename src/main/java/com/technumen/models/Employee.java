package com.technumen.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@ToString(exclude = {"empPassword", "empPassword2"})
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<Timesheet> timesheetRecords = new HashSet<>(0);

    public String getEmployeeFullName() {
        return (StringUtils.isNotBlank(this.employeeFirstName) ? this.employeeFirstName : "") +
                " " + (StringUtils.isNotBlank(this.employeeLastName) ? this.employeeLastName : "");
    }

}
