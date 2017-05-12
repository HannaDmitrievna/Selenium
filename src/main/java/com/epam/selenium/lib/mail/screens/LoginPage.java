package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(name = "login")
    private WebElement loginInput;

    @FindBy(name = "passwd")
    private WebElement passwordInput;

    @FindBy(css = ".domik2__submit button")
    private WebElement enterButton;

    public void enterLogin(String email) {
        Logger.debug("Enter login " + email);
        browser.type(loginInput, email);
    }

    public void enterPassword(String password) {
        Logger.debug("Enter password " + password);
        browser.type(passwordInput, password);
    }

    public void submitButtonClick() {
        Logger.debug("Clicking on submit button");
        browser.click(enterButton);
    }
}
