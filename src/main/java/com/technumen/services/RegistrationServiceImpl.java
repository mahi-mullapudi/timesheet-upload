package com.technumen.services;

import com.technumen.models.Employee;
import com.technumen.repositories.EmployeeRepository;
import com.technumen.utils.AuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    AuthenticationUtils authUtils;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void saveRegistrationDetails(Employee employee) throws Exception {
        log.info("Inside saveRegistrationDetails of RegistrationServiceImpl: " + employee.toString());
        employee.setEmpPassword(authUtils.getHashValue(employee.getEmpPassword())); //Generating Hash for the password.
        employeeRepository.save(employee);
    }
}
