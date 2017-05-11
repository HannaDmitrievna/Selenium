package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComposePage extends BasePage {
    @FindBy(xpath = "//*[@data-params='field=to']//ancestor::tr//input[@type='text']")
    WebElement addressInput;

    @FindBy(name = "subj")
    WebElement subjectInput;

    @FindBy(css = "body#tinymce")
    WebElement postInput;

    @FindBy(id = "compose-send_ifr")
    WebElement postFrame;

    @FindBy(id = "compose-submit")
    WebElement submitButton;

    @FindBy(css = "div[data-compose-type='letter postcard'] .b-compose-message__actions__helper_saved")
    WebElement savingNotification;

    @FindBy(css = "span.b-notification__i")
    WebElement notification;

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
        browser.current().getWrappedDriver().switchTo().defaultContent();
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
