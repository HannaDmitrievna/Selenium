package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComposePage extends BasePage {
    @FindBy(className = "js-suggest-proxy _init ui-autocomplete-input")
    private WebElement addressInput;

    @FindBy(className = "mail-Compose-Field-Input-Controller js-compose-field js-editor-tabfocus-prev")
    private WebElement subjectInput;

    @FindBy(css = "body#tinymce")
    private WebElement postInput;

    @FindBy(id = "compose-send_ifr")
    private WebElement postFrame;

    @FindBy(id = "compose-submit")
    private WebElement submitButton;

    @FindBy(css = "div[data-compose-type='letter postcard'] .b-compose-message__actions__helper_saved")
    private WebElement savingNotification;

    @FindBy(css = "span.b-notification__i")
    private WebElement notification;

    public void enterAddress(String email) {
        Logger.debug("Entering email: " + email);
        browser.type(addressInput, email);
    }

    public void enterSubject(String subject) {
        Logger.debug("Entering subject: " + subject);
        browser.type(subjectInput, subject);
    }

    public void enterPost(String post) {
        Logger.debug("Enering post: " + post);
        browser.type(postInput, post);
    }

    public void switchToPostFrame() {
        Logger.debug("Switch to " + postFrame.toString());
        browser.switchTo(postFrame);
    }

    public void switchToMainFrame() {
        Logger.debug("Switching to main frame");
        browser.getWrappedDriver().switchTo().defaultContent();
    }

    public void clickOnSendButton() {
        Logger.debug("Clicking on send button");
        browser.click(submitButton);
    }

    public boolean isNotificationPresent() {
        Logger.debug("Waiting notification presenting");
        return notification.isEnabled();
    }

    public boolean isSavingNotificationPresent() {
        Logger.debug("Waiting notification of saving in draft");
        browser.waitElement(savingNotification);
        return savingNotification.isEnabled();
    }
}
