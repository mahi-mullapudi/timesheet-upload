package com.technumen.daos;

import com.technumen.models.Employee;

public interface RegistrationDao {
    /**
     * Persists the User Registration details into the database table.
     * Also returns back the primary key (EmployeeId) of the table.
     *
     * @param employee
     * @return
     * @throws Exception
     */
    public long saveRegistrationDetails(Employee employee) throws Exception;
}
