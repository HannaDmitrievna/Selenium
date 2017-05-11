package com.epam.selenium.tests.mail;

import com.epam.selenium.framework.ui.Browser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.selenium.lib.mail.services.LoginService.enterWithWrongPasswordFromDataProvider;
import static com.epam.selenium.lib.mail.services.LoginService.getErrorMessage;

public class LoginWithWrongPasswordUsingDPTest extends BaseMailTest {

    private static final String EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS = "Неправильный логин или пароль.";

    @BeforeMethod(description = "Preparing browser")
    public void preparingBrowser() {
        browser = Browser.rise();
        browser.open(System.getProperty("yandex.mail.link"));
    }

    @Test(dataProvider = "valuesDp", description = "Check expected error message in case of wrong password")
    public void checkErrorMessageWrongPassword(String password) {
        enterWithWrongPasswordFromDataProvider(password);
        Assert.assertEquals(getErrorMessage(), EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS);
    }

    @AfterMethod(description = "Kill driver")
    public void killDriver() {
        Browser.current().quit();
    }

    @DataProvider(name = "valuesDp")
    public static Object[][] valuesForCheck() {
        return new Object[][] {
                {"1234567890"},
                {"qwertyuiop"},
                {"1234qwer"},
                {"1q2e3r4t"}
        };
    }
}
