package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import com.epam.selenium.framework.ui.Browser;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginErrorPage extends BasePage {
    @FindBy(className = "error-msg")
    private WebElement errorMessage;

    @FindBy(className = "passport-Domik-Form-Error passport-Domik-Form-Error_active")
    private WebElement errorMessageWithAnotherDomik;

    public String getErrorMassage() {
        Logger.debug("Getting error message");
        final String errMessage = Browser.current().getText(errorMessage);

//        if (!errMessage.isEmpty()) {
//            return errMessage;
//        } else {
//            return Browser.current().getText(errorMessageWithAnotherDomik);
//        }

        try {
            return Browser.current().getText(errorMessage);
        } catch (InvalidSelectorException e) {
            return Browser.current().getText(errorMessageWithAnotherDomik);
        }
    }
}
