package com.epam.selenium.tests.mail;

import com.epam.selenium.lib.mail.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.selenium.lib.mail.services.MailService.isLetterSent;
import static com.epam.selenium.lib.mail.services.MailService.sendFullLetter;

public class SendFullLetterTest extends BaseMailTest {

    @BeforeMethod(description = "Enter in mailbox")
    public void login() {
        LoginService.enterWithRightCredential();
    }

    @Test(description = "Checking sending of full letter")
    public void checkSendFullLetter() throws InterruptedException {
        sendFullLetter();
        Assert.assertTrue(isLetterSent());
    }
}
