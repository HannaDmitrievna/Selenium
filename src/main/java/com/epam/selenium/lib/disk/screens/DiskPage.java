package com.epam.selenium.lib.disk.screens;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DiskPage extends BasePage{
    @FindBy(className = "button-upload__attach")
    WebElement attachButton;

    @FindBy(css = ".b-item-upload__icon.b-item-upload__icon_done")
    WebElement uploadNotification;

    @FindBy(css = "._nb-popup-close.ns-action.js-cross")
    WebElement closeUploadPopup;

    @FindBy(className = "header__title")
    WebElement mainPageLink;

    @FindBy(className = "nb-resource__text-name")
    List<WebElement> existingFiles;

    @FindBy(css = ".notifications__item.nb-island.notifications__item_moved")
    WebElement deleteNotification;

    @FindBy(xpath = "//div[@data-id='/trash']")
    WebElement trashLocator;

    Actions action;

    WebElement element;

    public void open() {
        Logger.info("Open disk page");
        browser.click(mainPageLink);
    }

    public void refresh() {
        Logger.debug("Refreshing disk page");
        browser.refresh();
    }

    public void attachFile(String path) {
        Logger.debug("Attaching file: " + path);
        browser.sendKeys(attachButton, path);
    }

    public void waitUploadForNotification() {
        Logger.debug("Waiting upload notification");
        browser.waitElement(uploadNotification);
    }

    public void closePopup() {
        Logger.debug("Closing popup");
        browser.click(closeUploadPopup);
    }

    public boolean isFilePresent(String fileName) {
        Logger.debug("Checking presenting of file: " + fileName);
        open();
        return browser.isPresent(existingFiles, fileName);
    }

    public void chooseFile(String fileName) {
        open();
        Logger.debug("Choosing file: " + fileName);
        browser.waitElement(existingFiles.get(0));
        for (WebElement file : existingFiles) {
            if (file.getText().equals(fileName)) {
                browser.click(file);
            }
        }
    }

    public void downloadFile() {
        Logger.debug("Downloading file");
        browser.click(browser.getWrappedDriver().findElement(By.xpath("//*[@data-click-action='resource.download']")));
    }

    public void deleteFile() {
        Logger.debug("Deleting file");
        browser.click(browser.getWrappedDriver().findElement(By.xpath("//div[contains(@class, 'b-aside js-prevent-deselect ns-view-visible')]//*[@data-click-action='resource.delete']")));
    }

    public void waitDeleteNotification() {
        Logger.debug("Waiting delete notification");
        browser.waitElement(deleteNotification);
    }

    public void chooseFiles(List<String> fileNames) {
        open();
        Logger.debug("Selecting of files");
        browser.waitElement(existingFiles.get(existingFiles.size() - 1));
        element = null;
        action = new Actions(browser.getWrappedDriver());
        action.keyDown(Keys.CONTROL);
        for (WebElement file : existingFiles) {
            for (String fileName : fileNames)
                if (file.getText().equals(fileName)) {
                    element = file;
                    action.moveToElement(file).click();
                }
        }
        action.keyUp(Keys.CONTROL).build().perform();
    }

    public void deleteFiles() {
        Logger.debug("Drag and drop files");
        action.dragAndDrop(element, trashLocator);
        action.build().perform();
    }
}
