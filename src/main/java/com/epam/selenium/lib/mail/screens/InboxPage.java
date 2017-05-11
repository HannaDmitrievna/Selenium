package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InboxPage extends BasePage {
    @FindBy(css = ".mail-User-Name")
    WebElement userEmailLocator;

    @FindBy(xpath = "//a[@href='#compose']")
    WebElement writeNewLetterLink;

    public String getUserEmail() {
        Logger.debug("Getting user email");
        return browser.getText(userEmailLocator);
    }

    public void goToLetterForm() {
        Logger.debug("Go to letter form");
        browser.click(writeNewLetterLink);
    }

    public boolean isLetterPresent(String emailId) {
        Logger.debug("Check presenting letter with Id " + emailId + " in inbox");
        return browser.isPresent(By.xpath(String.format("//a[@href='%s']", emailId)));
    }
}
