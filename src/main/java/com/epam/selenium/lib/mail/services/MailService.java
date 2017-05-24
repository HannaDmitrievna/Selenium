package com.epam.selenium.lib.mail.services;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.lib.mail.Letter;
import com.epam.selenium.lib.mail.LetterBuilder;
import com.epam.selenium.lib.mail.screens.*;
import org.openqa.selenium.support.PageFactory;

import static com.epam.selenium.framework.ui.Browser.current;

public class MailService {

    public static void sendFullLetter() {
        Logger.info("Sending full letter");
        ComposePage composePage = PageFactory.initElements(current().getWrappedDriver(), ComposePage.class);
        wrightLetter();
        composePage.clickOnSendButton();
    }

    public static void sendEmptyLetter() {
        Logger.info("Sending empty letter");
        InboxPage inboxPage = PageFactory.initElements(current().getWrappedDriver(), InboxPage.class);
        inboxPage.goToLetterForm();
        ComposePage composePage = PageFactory.initElements(current().getWrappedDriver(), ComposePage.class);
        composePage.clickOnSendButton();
    }

    public static void sendLetterWithAddress() {
        Logger.info("Sending letter with address");
        InboxPage inboxPage = PageFactory.initElements(current().getWrappedDriver(), InboxPage.class);
        inboxPage.goToLetterForm();
        ComposePage composePage = PageFactory.initElements(current().getWrappedDriver(), ComposePage.class);
        composePage.enterAddress(LetterBuilder.getLetter().getAddress());
        composePage.clickOnSendButton();
    }

    private static void wrightLetter() {
        Logger.info("Wrighting letter");
        InboxPage inboxPage = PageFactory.initElements(current().getWrappedDriver(), InboxPage.class);
        inboxPage.goToLetterForm();
        ComposePage composePage = PageFactory.initElements(current().getWrappedDriver(), ComposePage.class);
        final Letter letter = LetterBuilder.getLetter();

        composePage.enterAddress(letter.getAddress());
        composePage.enterSubject(letter.getSubject());
        composePage.enterPost(letter.getPost());
    }

    public static boolean isLetterSent() {

        Logger.info("Checking if the letter was sent");
        final ConfirmPage confirmPage = new ConfirmPage();

        return confirmPage.isLetterSent();
    }

    public static boolean isNotificationPresent() {
        Logger.info("Checking presenting of notification");
        ComposePage composePage = PageFactory.initElements(current().getWrappedDriver(), ComposePage.class);
        return composePage.isNotificationPresent();
    }

    public static boolean saveLetterInDraftAndCheckIt() {
        Logger.info("Saving letter in draft");
        String subject = LetterBuilder.getLetter().getSubject();
        InboxPage inboxPage = PageFactory.initElements(current().getWrappedDriver(), InboxPage.class);
        inboxPage.goToLetterForm();
        ComposePage composePage = PageFactory.initElements(current().getWrappedDriver(), ComposePage.class);
        composePage.enterAddress(LetterBuilder.getLetter().getAddress());
        composePage.enterSubject(subject);
        composePage.switchToPostFrame();
        composePage.enterPost(LetterBuilder.getLetter().getPost());
        composePage.switchToMainFrame();
        DraftPage draftPage = PageFactory.initElements(current().getWrappedDriver(), DraftPage.class);
        composePage.isSavingNotificationPresent();
        draftPage.open();
        return draftPage.isLetterPresent(subject) && deleteLetterAndCheckIt(subject);
    }

    private static boolean deleteLetterAndCheckIt(String subject) {
        Logger.info("Deleting letter");
        boolean isLetterInDraftAfterDeleting;
        boolean isLetterInTrash;
        boolean isLetterInTrashAfterDeleting;
        DraftPage draftPage = PageFactory.initElements(current().getWrappedDriver(), DraftPage.class);
        draftPage.selectCheckbox(subject);
        draftPage.clickDelete();
        try {
            isLetterInDraftAfterDeleting = draftPage.isLetterPresent(subject);
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
            isLetterInDraftAfterDeleting = false;
        }
        TrashPage trashPage = PageFactory.initElements(current().getWrappedDriver(), TrashPage.class);
        isLetterInTrash = trashPage.isLetterPresent(subject);
        trashPage.clickOnClearTrash();
        trashPage.confirmClearingTrash();
        try {
            isLetterInTrashAfterDeleting = trashPage.isLetterPresent(subject);
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
            isLetterInTrashAfterDeleting = false;
        }
        return !isLetterInDraftAfterDeleting && isLetterInTrash && !isLetterInTrashAfterDeleting;
    }
}