package com.epam.selenium.tests.mail;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.selenium.lib.mail.services.LoginService.enterWithWrongPassword;
import static com.epam.selenium.lib.mail.services.LoginService.getErrorMessage;

public class LoginWithWrongPasswordTest extends BaseMailTest {

    private static final String EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS = "Неправильный логин или пароль.";

    @Test(description = "Check expected error message in case of wrong password")
    public void checkErrorMessageWrongPassword() {
        enterWithWrongPassword();
        Assert.assertEquals(getErrorMessage(), EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS);
    }
}