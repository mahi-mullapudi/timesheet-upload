package com.technumen.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Calendar;

@Component
@Scope(value = "prototype")
public class LoginActivity implements Serializable {

    private long loginActivityId;
    private long employeeId;
    private Calendar employeeLastLoggedIn;
    private Calendar employeeLastLoggedOut;
    private String clientIpAddress;

    public LoginActivity() {
    }

    public long getLoginActivityId() {
        return loginActivityId;
    }

    public void setLoginActivityId(long loginActivityId) {
        this.loginActivityId = loginActivityId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Calendar getEmployeeLastLoggedIn() {
        return employeeLastLoggedIn;
    }

    public void setEmployeeLastLoggedIn(Calendar employeeLastLoggedIn) {
        this.employeeLastLoggedIn = employeeLastLoggedIn;
    }

    public Calendar getEmployeeLastLoggedOut() {
        return employeeLastLoggedOut;
    }

    public void setEmployeeLastLoggedOut(Calendar employeeLastLoggedOut) {
        this.employeeLastLoggedOut = employeeLastLoggedOut;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }
}
