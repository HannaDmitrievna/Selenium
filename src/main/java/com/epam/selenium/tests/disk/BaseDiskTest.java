package com.epam.selenium.tests.disk;

import com.epam.selenium.framework.ui.Browser;
import com.epam.selenium.lib.disk.services.LoginService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseDiskTest {
    Browser browser;

    @BeforeClass(description = "Preparing browser")
    public void preparingBrowser() {
        browser = Browser.rise();
        browser.open(System.getProperty("yandex.disk.link"));
        LoginService.enterWithRightCredential();
    }

    @AfterClass(description = "Finishing browser")
    public void finishingBrowser() {
        browser.quit();
    }
}