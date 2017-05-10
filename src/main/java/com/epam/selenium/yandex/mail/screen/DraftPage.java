package com.epam.selenium.yandex.mail.screen;

import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftPage extends BasePage {
    @FindBy(css = "[data-action='delete']")
    WebElement deleteButton;

    @FindBy(css = "a[href$='#draft']")
    WebElement draftPageLink;

    private final String CHECKBOX_LOCATOR_PATTERN = "//*[@title='%s']/../../../../../../label/input[@type='checkbox']";
    private final String SUBJECT_LOCATOR_PATTERN = "span[title='%s']";

    public void open() {
        Logger.debug("Open draft page");
        browser.click(draftPageLink);
    }

    public void selectCheckbox(String subject) {
        Logger.debug("Selecting letter");
        open();
        browser.click(browser.getWrappedDriver().findElement(By.xpath(String.format(CHECKBOX_LOCATOR_PATTERN, subject))));
    }

    public void  clickDelete() {
        Logger.debug("Deleting letter");
        browser.click(deleteButton);
    }

    public boolean isLetterPresent(String subject) {
        Logger.debug("Checking presenting letter in draft");
        open();
        return browser.isPresent(By.cssSelector(String.format(SUBJECT_LOCATOR_PATTERN, subject)));
    }
}
