package com.technumen.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Component
@Scope("prototype")
@Data
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId; //Unique Id and the primary key for Employee table.
    private String employeeEmailId; //Primary EmailId/User Name of the user.
    @Transient
    private String empPassword; //This will be stored as a Hash value.
    @Transient
    private String empPassword2; //Just a confirmation field used for validation. Not saved/updated.
    private String assignedEmployeeId; //TechNumen assigned Identifier.
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeMiddleName;
    private String employeeTitle; //Description of the Employee Title.
    private long employeeRoleId; //Foreign Reference for Employee_Roles table.
    private String employeePhone;
    private String employeePhoneExt;
    private String clientName; //Name of the Client.
    private String clientAddress;//Address of the Client.
    private String clientCity;
    private String clientState;
    private String clientZip;
    private long departmentId;//Primary key of Department table.
    private String departmentName;
    private Date employeeStartDate; //Account Created Date.
    private String accountStatusFlag; //Flag to check if the account is Active or Inactive.
    private Date dteInactivated;
    private String nmeUserInactivated; // Who inactivated this user
    private Date dteCreated;

    public Employee() {
    }

}
