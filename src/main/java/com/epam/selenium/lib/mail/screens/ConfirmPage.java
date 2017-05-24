package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.By;

public class ConfirmPage extends BasePage {

    private final static By SUCCESS_SENT_LETTER_LOCATOR = By.xpath("//div[contains(@class, 'mail-Done-Title')]");

    public boolean isLetterSent() {
        return browser.isDisplayed(SUCCESS_SENT_LETTER_LOCATOR);
    }
}
