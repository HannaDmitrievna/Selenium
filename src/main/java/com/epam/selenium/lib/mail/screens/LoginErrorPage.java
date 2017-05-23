package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginErrorPage extends BasePage {

    @FindBy(className = "error-msg")
    private WebElement errorMessage;

    private final static By ERROR_MESSAGE_LOCATOR = By.className("error-msg");

    @FindBy(xpath = "//div[contains(@class, 'passport-Domik-Form-Error')]")
    private WebElement errorMessageWithAnotherDomik;

    public String getErrorMassage() {
        Logger.debug("Getting error message");

        if (browser.isDisplayed(ERROR_MESSAGE_LOCATOR)) {
            return browser.getText(errorMessage);
        } else {
            return browser.getText(errorMessageWithAnotherDomik);
        }
    }
}
