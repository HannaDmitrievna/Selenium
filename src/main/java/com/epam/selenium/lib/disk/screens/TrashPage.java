package com.epam.selenium.lib.disk.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TrashPage extends BasePage {
    @FindBy(css = "div[data-id='trash'] a[href$='/trash']")
    WebElement trashLink;

    @FindBy(css = "div.b-toolset__buttons button[data-click-action=\"trash.clean\"]")
    WebElement clearTrash;

    @FindBy(css = "button.nb-button._nb-small-action-button._init.b-confirmation__action.b-confirmation__action_right.js-confirmation-accept")
    WebElement confirmClearingTrash;

    @FindBy(className = "nb-resource__text-name")
    List<WebElement> existingFiles;

    @FindBy(className = "notifications__text")
    WebElement restoreNotification;

    private final String BUTTON_CHOOSE_PATTERN = "button[data-params='{\"id\":\"/trash/%s\"}']";

    public void open() {
        Logger.debug("Opening trash");
        browser.click(trashLink);
    }

    public void refresh() {
        Logger.debug("Refreshing trash page");
        browser.refresh();
    }

    public void clickOnClearTrash() {
        Logger.debug("Clear trash");
        browser.click(clearTrash);
    }

    public void confirmClearTrash() {
        Logger.debug("Confirm clearing trash");
        browser.click(confirmClearingTrash);
    }

    public boolean isFilePresent(String fileName) {
        Logger.debug("Checking presenting of file: " + fileName);
        boolean result = false;
        List<WebElement> listOfFiles = existingFiles;
        for (WebElement file : listOfFiles) {
            if (file.getText().equals(fileName)) {
                result = true;
            }
        }
        return result;
    }

    public void chooseFile(String fileName) {
        Logger.debug("Choosing of file: " + fileName);
        for (WebElement file : existingFiles) {
            if (file.getText().equals(fileName)) {
                browser.click(file);
            }
        }
    }

    public void restoreFile(String fileName) {
        Logger.debug("Restoring file: " + fileName);
        browser.click(browser.getWrappedDriver().findElements(By.cssSelector(String.format(BUTTON_CHOOSE_PATTERN, fileName))).get(0));
    }

    public void waitRestoring() {
        Logger.debug("Restoring file");

        browser.waitElement(restoreNotification);
    }
}
