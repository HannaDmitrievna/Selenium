package com.epam.selenium.lib.disk.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(name = "login")
    private WebElement loginInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = ".nb-button._nb-normal-button._init.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only")
    private WebElement enterButton;

    public void enterLogin(String email) {
        Logger.debug("Entering login: " + email);
        browser.type(loginInput, email);
    }

    public void enterPassword(String password) {
        Logger.debug("Entering password: " + password);
        browser.type(passwordInput, password);
    }

    public void submitButtonClick() {
        Logger.debug("Clicking submit button");
        browser.click(enterButton);
    }
}
