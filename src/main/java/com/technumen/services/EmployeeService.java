package com.technumen.services;

import com.technumen.models.Employee;

public interface EmployeeService {
    /**
     * Returns the Employee object based on a given employeeId.
     *
     * @param employeeId
     * @return
     */
    public Employee getEmployeeByEmpId(long employeeId);

    /**
     * Get all employees as an Iterable list of Employees.
     *
     * @return
     */
    public Iterable<Employee> getAllEmployees();

}
