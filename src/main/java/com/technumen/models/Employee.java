package com.technumen.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Scope("prototype")
@Data
public class Employee implements Serializable {

    private long employeeId; //Unique Id and the primary key for Employee Id.
    private String employeeEmailId; //This will be the primary EmailId of the user.
    private String empPassword; //This will be stored as a Hash value and never retrieved.
    private String empPassword2; //Just a confirmation field used for validation. Not saved/updated.
    private String assignedEmployeeId; //This is the Employee Id that TechNumen assigned.
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeMiddleName;
    private String empTitle; //This is the description of the employee role.
    private long employeeRoleId; //This maintains the Id for the employee role.
    private String phoneNumber;
    private String phoneExt;
    private String clientName; //Name of the client for which the employee is working for.
    private String clientAddress;//Address of the client for which the employee is working for.
    private String clientCity;
    private String clientState;
    private String clientZip;
    private long departmentId;
    private String departmentName;
    private Date startDate; //Account Created Date.
    private String activeFlag; //Flag to check if the account is Active or Inactive.
    private Date inactivatedDate;
    private String inactivatedBy; // This contains the user info of who inactivated this user

    //TODO Include additional fields based on the requirements.

    public Employee() {
    }

}
