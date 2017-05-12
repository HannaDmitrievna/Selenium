package com.epam.selenium.tests.mail;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.selenium.lib.mail.services.LoginService.enterWithWrongCredential;
import static com.epam.selenium.lib.mail.services.LoginService.getErrorMessage;

public class LoginWithWrongCredentialTest extends BaseMailTest {

    private static final String EXPECTED_ERROR_MESSAGE_IN_CASE_NON_EXISTED_ACCOUNT = "Нет аккаунта с таким логином.";

    @Test(description = "Check expected error in case of non existed account")
    public void checkErrorMessageNonExistedAccount() {
        enterWithWrongCredential();
        Assert.assertEquals(getErrorMessage(), EXPECTED_ERROR_MESSAGE_IN_CASE_NON_EXISTED_ACCOUNT);
    }
}