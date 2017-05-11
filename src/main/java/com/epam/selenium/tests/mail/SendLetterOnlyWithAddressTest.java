package com.epam.selenium.tests.mail;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.selenium.lib.mail.services.LoginService.enterWithRightCredential;
import static com.epam.selenium.lib.mail.services.MailService.isLetterPresentIntoInboxAndOutbox;
import static com.epam.selenium.lib.mail.services.MailService.sendLetterWithAddress;

public class SendLetterOnlyWithAddressTest extends BaseMailTest {
    @BeforeMethod(description = "Enter in mailbox")
    public void login() {
        enterWithRightCredential();
    }

    @Test(description = "Checking sending of letter with address")
    public void checkSendLetterWithAddress() {
        sendLetterWithAddress();
        Assert.assertTrue(isLetterPresentIntoInboxAndOutbox());
    }
}