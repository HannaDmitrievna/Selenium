package com.epam.selenium.lib.mail.services;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.lib.mail.screens.*;
import com.epam.selenium.lib.mail.LetterBuilder;
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
        composePage.enterAddress(LetterBuilder.getLetter().getAddress());
        composePage.enterSubject(LetterBuilder.getLetter().getSubject());
        composePage.switchToPostFrame();
        composePage.enterPost(LetterBuilder.getLetter().getPost());
        composePage.switchToMainFrame();
    }

    public static boolean isLetterPresentIntoInboxAndOutbox() {
        Logger.info("Checking presenting letter it inbox and outbox");
        ConfirmPage confirmPage = PageFactory.initElements(current().getWrappedDriver(), ConfirmPage.class);
        InboxPage inboxPage = PageFactory.initElements(current().getWrappedDriver(), InboxPage.class);
        OutboxPage outboxPage = PageFactory.initElements(current().getWrappedDriver(), OutboxPage.class);
        String mailLink = confirmPage.returnLetterLink();
        return inboxPage.isLetterPresent(mailLink.substring(mailLink.indexOf("#"))) && outboxPage.isLetterPresent(mailLink.substring(mailLink.indexOf("#")));
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