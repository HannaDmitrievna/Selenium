package com.epam.selenium.tests.mail;

import com.epam.selenium.lib.mail.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.selenium.lib.mail.services.MailService.isNotificationPresent;
import static com.epam.selenium.lib.mail.services.MailService.sendEmptyLetter;

public class SendEmptyLetterTest extends BaseMailTest {

    @BeforeMethod(description = "Enter in mailbox")
    public void login() {
        LoginService.enterWithRightCredential();
    }

    @Test(description = "Checking sending of empty letter")
    public void checkSendEmptyLetter() throws InterruptedException {
        sendEmptyLetter();
        Assert.assertTrue(isNotificationPresent());
    }
}