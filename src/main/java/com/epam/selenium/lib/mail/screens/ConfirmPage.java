package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmPage extends BasePage{
    @FindBy(xpath = "//a[@class='b-statusline__link']")
    WebElement confirmationMessage;

    public String returnLetterLink() {
        Logger.debug("Return letter link");
        return browser.getAttribute(confirmationMessage, "href");
    }
}
