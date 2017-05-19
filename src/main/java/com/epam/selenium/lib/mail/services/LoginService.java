package com.epam.selenium.lib.mail.services;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.lib.common.Account;
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
        final LoginPage loginPage = PageFactory.initElements(current().getWrappedDriver(), LoginPage.class);
        final Account account = AccountBuilder.getDefaultAccount();

        loginPage.enterLogin(account.getLogin());
        loginPage.enterPassword(account.getPassword());
        loginPage.submitButtonClick();
    }

    public static void enterWithWrongCredential() {
        Logger.info("Entering with wrong credential");
        final LoginPage loginPage = PageFactory.initElements(current().getWrappedDriver(), LoginPage.class);
        final Account accountWithWrongCredential = AccountBuilder.getAccountWithWrongCred();

        loginPage.enterLogin(accountWithWrongCredential.getLogin());
        loginPage.enterPassword(accountWithWrongCredential.getPassword());
        loginPage.submitButtonClick();
    }

    public static void enterWithWrongPassword() {
        Logger.info("Entering with wrong password");
        final LoginPage loginPage = PageFactory.initElements(current().getWrappedDriver(), LoginPage.class);
        final Account accountWithWrongPassword = AccountBuilder.getAccountWithWrongPass();

        loginPage.enterLogin(accountWithWrongPassword.getLogin());
        loginPage.enterPassword(accountWithWrongPassword.getPassword());
        loginPage.submitButtonClick();
    }

    public static void enterWithWrongPasswordFromDataProvider(final String password) {
        Logger.info("Entering with wrong password");
        final LoginPage loginPage = PageFactory.initElements(current().getWrappedDriver(), LoginPage.class);
        loginPage.enterLogin(AccountBuilder.getAccountWithWrongPass().getLogin());
        loginPage.enterPassword(password);
        loginPage.submitButtonClick();
    }

    public static String getUserEmail() {
        Logger.info("Getting user e-mail");
        final InboxPage inboxPage = PageFactory.initElements(current().getWrappedDriver(), InboxPage.class);
        return inboxPage.getUserEmail();
    }

    public static String getErrorMessage() {
        Logger.info("Getting error message");
        final LoginErrorPage loginErrorPage = PageFactory.initElements(current().getWrappedDriver(), LoginErrorPage.class);
        return loginErrorPage.getErrorMassage();
    }

    public static String getErrorMessageFromPassportPage() {
        Logger.info("Getting error message");
        final PassportPage loginErrorPage = PageFactory.initElements(current().getWrappedDriver(), PassportPage.class);
        return loginErrorPage.getErrorMassage();
    }
}
