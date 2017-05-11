package com.epam.selenium.lib.disk.services;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.lib.common.AccountBuilder;
import com.epam.selenium.lib.disk.screens.LoginPage;
import org.openqa.selenium.support.PageFactory;

import static com.epam.selenium.framework.ui.Browser.current;

public class LoginService {
    public static void enterWithRightCredential() {
        Logger.info("Entering with right credential");
        LoginPage loginPage = PageFactory.initElements(current().getWrappedDriver(), LoginPage.class);
        loginPage.enterLogin(AccountBuilder.getDefaultAccount().getLogin());
        loginPage.enterPassword(AccountBuilder.getDefaultAccount().getPassword());
        loginPage.submitButtonClick();
    }
}
