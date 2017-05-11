package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import com.epam.selenium.framework.ui.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PassportPage extends BasePage {
    @FindBy(className = "passport-Domik-Form-Error passport-Domik-Form-Error_active")
    WebElement errorMessage;

    public String getErrorMassage() {
        Logger.debug("Getting error message");
        return Browser.current().getText(errorMessage);
    }
}
