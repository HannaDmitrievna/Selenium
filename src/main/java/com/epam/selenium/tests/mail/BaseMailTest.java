package com.epam.selenium.tests.mail;

import com.epam.selenium.framework.ui.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseMailTest {
    Browser browser;

    @BeforeClass(description = "Preparing browser")
    public void preparingBrowser() {
        browser = Browser.rise();
        browser.open(System.getProperty("yandex.mail.link"));
    }

    @AfterClass(description = "Finishing browser")
    public void finishingBrowser() {
        browser.quit();
    }
}
