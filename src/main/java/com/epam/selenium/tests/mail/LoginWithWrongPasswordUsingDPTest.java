package com.epam.selenium.tests.mail;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.selenium.lib.mail.services.LoginService.enterWithWrongPasswordFromDataProvider;
import static com.epam.selenium.lib.mail.services.LoginService.getErrorMessage;
import static org.testng.Assert.assertTrue;

public class LoginWithWrongPasswordUsingDPTest extends BaseMailTest {

    private static final String EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS_1 = "Неправильный логин или пароль.";
    private static final String EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS_2 = "Неверный пароль.";

    @Test(dataProvider = "valuesDp", description = "Check expected error message in case of wrong password")
    public void checkErrorMessageWrongPassword(String password) {
        enterWithWrongPasswordFromDataProvider(password);

        final String errorMessage = getErrorMessage();
        assertTrue(
                errorMessage.equals(EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS_1) ||
                        errorMessage.equals(EXPECTED_ERROR_MESSAGE_IN_CASE_WRONG_PASS_2));
    }

    @DataProvider(name = "valuesDp")
    public static Object[][] valuesForCheck() {
        return new Object[][]{
                {"1234567890"},
                {"qwertyuiop"},
                {"1234qwer"},
                {"1q2e3r4t"}
        };
    }
}
