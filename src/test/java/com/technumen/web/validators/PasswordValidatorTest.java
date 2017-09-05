package com.technumen.web.validators;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

public class PasswordValidatorTest {

    PasswordValidator passwordValidator;

    /**
     * Tests for checking the right inputs and see if the method is working as expected.
     *
     * @throws Exception
     */
    @Test
    public void validatePositiveScenarios() throws Exception {
        passwordValidator = new PasswordValidator();
        assertEquals("Allowing wrong Inputs", passwordValidator.validate("Test#123"), true);
        assertEquals("Allowing wrong Inputs", passwordValidator.validate("@#$jasdfas1W"), true);
        assertEquals("Allowing wrong Inputs", passwordValidator.validate("$jasdfas1W$jasdfas1W"), true);
    }

    /**
     * Tests for checking the wrong inputs and see if the method is working as expected.
     *
     * @throws Exception
     */
    @Test
    public void validateNegativeScenarios() throws Exception {
        passwordValidator = new PasswordValidator();
        assertEquals("Allowing less Characters", passwordValidator.validate("Nj#1"), false);
        assertEquals("Allowing more Characters", passwordValidator.validate("Nj#1Nj#1Nj#1Nj#1Nj#1Nj#1Nj#1"), false);
        assertEquals("Allowing unwanted Characters", passwordValidator.validate("()**&&^&"), false);
        assertEquals("Allowing inputs without lower case", passwordValidator.validate("TEST@123"), false);
        assertEquals("Allowing inputs without upper case", passwordValidator.validate("test@123"), false);
        assertEquals("Allowing inputs without digit", passwordValidator.validate("test@TEST"), false);
        assertEquals("Allowing inputs without special characters", passwordValidator.validate("test123TEST"), false);
    }
}