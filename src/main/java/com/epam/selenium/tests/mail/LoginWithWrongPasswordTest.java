package com.epam.selenium.tests.mail;

import org.testng.annotations.Test;

import static com.epam.selenium.lib.mail.services.LoginService.enterWithWrongPassword;
import static com.epam.selenium.lib.mail.services.LoginService.getErrorMessage;
import static org.testng.Assert.assertTrue;

public class LoginWithWrongPasswordTest extends BaseMailTest {

    private static final String EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS_1 = "Неправильный логин или пароль.";
    private static final String EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS_2 = "Неверный пароль.";

    @Test(description = "Check expected error message in case of wrong password")
    public void checkErrorMessageWrongPassword() {
        enterWithWrongPassword();

        final String errorMessage = getErrorMessage();
        assertTrue(
                errorMessage.equals(EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS_1) ||
                        errorMessage.equals(EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS_2));
    }
}