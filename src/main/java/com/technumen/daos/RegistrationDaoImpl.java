package com.technumen.daos;

import com.technumen.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RegistrationDaoImpl implements RegistrationDao {

    @Override
    public long saveRegistrationDetails(Employee employee) throws Exception {
        log.info("Inside saveRegistrationDetails method of RegistrationDaoImpl --> " + employee.toString());

        return 0;
    }
}