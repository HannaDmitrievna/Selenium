package com.epam.selenium.lib.mail.services;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.lib.common.AccountBuilder;
import com.epam.selenium.lib.mail.screens.InboxPage;
import com.epam.selenium.lib.mail.screens.LoginErrorPage;
import com.epam.selenium.lib.mail.screens.LoginPage;
import com.epam.selenium.lib.mail.screens.PassportPage;
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

    public static void enterWithWrongCredential() {
        Logger.info("Entering with wrong credential");
        LoginPage loginPage = PageFactory.initElements(current().getWrappedDriver(), LoginPage.class);
        loginPage.enterLogin(AccountBuilder.getAccountWithWrongCred().getLogin());
        loginPage.enterPassword(AccountBuilder.getAccountWithWrongCred().getPassword());
        loginPage.submitButtonClick();
    }

    public static void enterWithWrongPassword() {
        Logger.info("Entering with wrong password");
        LoginPage loginPage = PageFactory.initElements(current().getWrappedDriver(), LoginPage.class);
        loginPage.enterLogin(AccountBuilder.getAccountWithWrongPass().getLogin());
        loginPage.enterPassword(AccountBuilder.getAccountWithWrongPass().getPassword());
        loginPage.submitButtonClick();
    }

    public static void enterWithWrongPasswordFromDataProvider(String password) {
        Logger.info("Entering with wrong password");
        LoginPage loginPage = PageFactory.initElements(current().getWrappedDriver(), LoginPage.class);
        loginPage.enterLogin(AccountBuilder.getAccountWithWrongPass().getLogin());
        loginPage.enterPassword(password);
        loginPage.submitButtonClick();
    }

    public static String getUserEmail() {
        Logger.info("Getting user e-mail");
        InboxPage inboxPage = PageFactory.initElements(current().getWrappedDriver(), InboxPage.class);
        return inboxPage.getUserEmail();
    }

    public static String getErrorMessage() {
        Logger.info("Getting error message");
        LoginErrorPage loginErrorPage = PageFactory.initElements(current().getWrappedDriver(), LoginErrorPage.class);
        return loginErrorPage.getErrorMassage();
    }

    public static String getErrorMessageFromPassportPage() {
        Logger.info("Getting error message");
        PassportPage loginErrorPage = PageFactory.initElements(current().getWrappedDriver(), PassportPage.class);
        return loginErrorPage.getErrorMassage();
    }
}
