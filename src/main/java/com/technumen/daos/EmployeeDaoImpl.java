package com.technumen.daos;

import com.google.common.base.Stopwatch;
import com.technumen.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    Employee employee;

    private Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Override
    public Employee getEmployeeByEmpId(long empId) throws Exception {

        Stopwatch timer = Stopwatch.createStarted();
        logger.info("Inside getEmployeeByEmpId method of EmployeeDaoImpl ");
        employee = new Employee(12345, "Mahidhar", "Mullapudi",
                "C", "Associate", 11111, 22222,
                "Library", 555555, "Poorna Maddipatla",
                666666, "Sriram", new Date(), true, new Date(), "Admin");
        logger.info("getEmployeeByEmpId method took: " + timer.stop());
        return employee;
    }

}
