package com.technumen.repositories;

import com.technumen.models.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public Employee findEmployeeByEmployeeEmailId(String emailId);

    public int countEmployeeByEmployeeEmailId(String emailId);

    public int countEmployeeByEmployeeEmailIdAndEmpPassword(String emailId, String password);

    @Modifying
    @Query("UPDATE Employee emp SET emp.empPassword = :password where emp.employeeEmailId = :emailId")
    public int updatePassword(@Param("password") String password, @Param("emailId") String emailId);

}
