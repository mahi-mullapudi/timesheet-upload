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

    /**
     * Update the password with the temporary password by given Email Id.
     *
     * @param password
     * @param emailId
     * @return
     * @throws Exception
     */
    public int updatePassword(String password, String emailId) throws Exception;
}
