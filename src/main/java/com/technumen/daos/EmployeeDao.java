package com.technumen.daos;

import com.technumen.models.Employee;

public interface EmployeeDao {

    Employee getEmployeeByEmpId(long empId) throws Exception;

}
