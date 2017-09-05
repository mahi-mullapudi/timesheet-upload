package com.technumen.services;

import com.technumen.models.Employee;

public interface RegistrationService {
    /**
     * Method to save User Registration details.
     * This method returns newly created EmployeeId after successful save.
     *
     * @param employee
     * @return
     * @throws Exception
     */
    public long saveRegistrationDetails(Employee employee) throws Exception;
}
