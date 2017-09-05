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
    private String assignedEmployeeId; //This is the Employee Id that TechNumen assigned.
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeMiddleName;
    private String empTitle; //This is the description of the employee role
    private long employeeRoleId; //This maintains the Id for the employee role
    private long departmentId;
    private String departmentName;
    private Date startDate; //Account Created Date.
    private String activeFlag; //Flag to check if the account is Active or Inactive.
    private Date inactivatedDate;
    private String inactivatedBy; // This contains the user info of who inactivated this user
    private String clientName; //Name of the client for which the employee is working for.
    private String clientAddress;//Address of the client for which the employee is working for.
    //TODO Include additional fields based on the requirements.

    public Employee() {
    }

}
