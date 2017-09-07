package com.technumen.services;

import com.technumen.models.Employee;

public interface RegistrationService {
    /**
     * Method to save User Registration details.
     *
     * @param employee
     * @return
     * @throws Exception
     */
    public void saveRegistrationDetails(Employee employee) throws Exception;
}
