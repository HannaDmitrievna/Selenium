package com.epam.selenium.tests.mail;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.selenium.lib.common.AccountBuilder.getDefaultAccount;
import static com.epam.selenium.lib.mail.services.LoginService.enterWithRightCredential;
import static com.epam.selenium.lib.mail.services.LoginService.getUserEmail;

public class LoginPositiveTest extends BaseMailTest {

    @Test(description = "Positive Login TestParameters")
    public void checkPositiveLogin() {
        enterWithRightCredential();
        Assert.assertEquals(getUserEmail(), getDefaultAccount().getEmail());
    }
}