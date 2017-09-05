package com.technumen.services;

import com.technumen.daos.RegistrationDao;
import com.technumen.models.Employee;
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
    RegistrationDao registrationDao;

    @Override
    public long saveRegistrationDetails(Employee employee) throws Exception {
        log.info("Inside saveRegistrationDetails of RegistrationServiceImpl");
        employee.setEmpPassword(authUtils.getHashValue(employee.getEmpPassword())); //Generating Hash for the password.
        return registrationDao.saveRegistrationDetails(employee);
    }
}
