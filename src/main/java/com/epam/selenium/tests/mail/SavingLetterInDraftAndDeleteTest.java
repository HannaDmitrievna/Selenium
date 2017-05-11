package com.epam.selenium.tests.mail;

import com.epam.selenium.lib.mail.services.LoginService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.selenium.lib.mail.services.MailService.saveLetterInDraftAndCheckIt;

public class SavingLetterInDraftAndDeleteTest extends BaseMailTest {

    @BeforeMethod(description = "Enter in mailbox")
    public void login() {
        LoginService.enterWithRightCredential();
    }

    @Test(description = "Saving in draft and deleting of letter")
    public void checkSaveInDraftAndDeleteLetter() throws InterruptedException {
        Assert.assertTrue(saveLetterInDraftAndCheckIt());
    }
}