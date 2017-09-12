package com.technumen.services;

import com.technumen.models.Employee;
import com.technumen.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee getEmployeeByEmployeeId(long employeeId) {
        log.info("Inside getEmployeeByEmployeeId method of EmployeeServiceImpl: " + employeeId);
        return employeeRepository.findOne(employeeId);
    }
}

