package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftPage extends BasePage {
    @FindBy(css = "[data-action='delete']")
    private WebElement deleteButton;

    @FindBy(xpath = "//a[@href='#draft']")
    private WebElement draftPageLink;

    private static final String CHECKBOX_LOCATOR_PATTERN =
            "//span[contains(@class, 'mail-MessageSnippet-Item')]//span[text()='%s']/../../../../preceding-sibling::span//label";
    private static final String SUBJECT_LOCATOR_PATTERN = "//span[text()='%s']";

    public void open() {
        Logger.debug("Open draft page");
        browser.click(draftPageLink);
    }

    public void selectCheckbox(String subject) {
        Logger.debug("Selecting letter");
        open();
        browser.click(browser.getWrappedDriver().findElement(By.xpath(String.format(CHECKBOX_LOCATOR_PATTERN, subject))));
    }

    public void clickDelete() {
        Logger.debug("Deleting letter");
        browser.click(deleteButton);
    }

    public boolean isLetterPresent(String subject) {
        Logger.debug("Checking presenting letter in draft");
        open();
        return browser.isPresent(By.xpath(String.format(SUBJECT_LOCATOR_PATTERN, subject)));
    }
}
