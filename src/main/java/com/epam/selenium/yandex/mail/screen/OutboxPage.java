package com.epam.selenium.yandex.mail.screen;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.By;

public class OutboxPage extends BasePage {
    public boolean isLetterPresent(String emailId) {
        Logger.debug("Checking presenting letter with Id " + emailId + " in outbox");
        return browser.isPresent(By.xpath(String.format("//a[@href='%s']", emailId))); }
}
