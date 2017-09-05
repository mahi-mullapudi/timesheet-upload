package com.technumen.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Scope("prototype")
@Data
public class Address implements Serializable {

    private long addressId;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phoneNum;
    private String phoneNum2;
    private String emailAddress;
    private String emailAddress2;

    public Address() {
    }

}
