package com.technumen.daos;

import com.technumen.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    Employee employee;

    private Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Override
    public Employee getEmployeeByEmpId(long empId) throws Exception {

        return new Employee();
    }

}
