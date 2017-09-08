package com.technumen.repositories;

import com.technumen.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public Employee findEmployeeByEmployeeId(long employeeId);

    public Employee findEmployeeByEmployeeEmailId(String emailId);

    public int countEmployeeByEmployeeEmailId(String emailId);

    public int countEmployeeByEmployeeEmailIdAndEmpPassword(String emailId, String password);

}
