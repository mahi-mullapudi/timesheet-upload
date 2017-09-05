package com.technumen.constants;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class TimesheetConstants implements Serializable {

    //Enumeration which maintain the timesheet status
    public enum Status {
        SAVED,
        SUBMITTED,
        APPROVED,
        REJECTED
    }

    public static final String SALT = "technumen";

}
