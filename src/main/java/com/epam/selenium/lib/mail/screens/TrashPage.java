package com.epam.selenium.lib.mail.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TrashPage extends BasePage {
    @FindBy(css = ".b-folders__folder__info img")
    private WebElement clearTrash;

    @FindBy(css = "div.b-popup__confirm button")
    private WebElement confirmClearTrash;

    @FindBy(xpath = "//a[@href='#trash']")
    private WebElement trashPageLink;

    public void open() {
        Logger.debug("Opening trash");
        browser.click(trashPageLink);
    }

    private static final String SUBJECT_LOCATOR_PATTERN = "span[title='%s']";

    public boolean isLetterPresent(String subject) {
        Logger.debug("Checking presenting letter with subject " + subject + " in trash");
        open();
        return browser.isPresent(By.cssSelector(String.format(SUBJECT_LOCATOR_PATTERN, subject)));
    }

    public void clickOnClearTrash() {
        Logger.debug("Clearing trash");
        browser.click(clearTrash);
    }

    public void confirmClearingTrash() {
        Logger.debug("Confirm clearing trash");
        browser.click(confirmClearTrash);
    }
}
