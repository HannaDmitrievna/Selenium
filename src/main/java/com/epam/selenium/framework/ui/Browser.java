package com.epam.selenium.framework.ui;

import com.epam.selenium.framework.config.GlobalConfig;
import com.epam.selenium.framework.reporting.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class Browser implements WrapsDriver {
    private static final byte COMMON_ELEMENT_WAIT_TIME_OUT = 15;
    private static final byte DRIVER_PAGE_LOAD_TIMEOUT_SECONDS = 20;
    private WebDriver driver;
    private static Map<Thread, Browser> instances = new HashMap<>();

    private Browser() {
    }

    public static synchronized Browser rise() {
        if (instances.get(Thread.currentThread()) != null) {
            instances.get(Thread.currentThread()).quit();
        }
        Browser browser = new Browser();
        browser.createDriver();
        instances.put(Thread.currentThread(), browser);
        return browser;
    }

    private void configBrowser() {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(DRIVER_PAGE_LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    public static synchronized Browser current() {
        if (instances.get(Thread.currentThread()) == null) {
            rise();
        }
        return instances.get(Thread.currentThread());
    }

    public void quit() {
        Logger.debug("Stop browser");
        try {
            WebDriver wrappedDriver = getWrappedDriver();
            if (wrappedDriver != null) {
                wrappedDriver.quit();
            }
        } catch (Exception ignore) {
			Logger.error(ignore.getMessage(), ignore);
		} finally {
            instances.remove(Thread.currentThread());
        }
    }

    private void createDriver() {
        switch (GlobalConfig.getInstance().getBrowserType()) {
            default:
            case FIREFOX:
                this.driver = localFirefoxDriver();
                break;
            case CHROME:
                this.driver = localChromeDriver();
                break;
        }
        configBrowser();
    }

    private WebDriver localFirefoxDriver() {
//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setPreference("browser.download.dir", FileService.PATH_FOR_DOWNLOADING);
//        profile.setPreference("browser.download.manager.showWhenStarting", false);
//        profile.setPreference("browser.download.folderList", 2);
//        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");
        if ("".equals(GlobalConfig.getInstance().getSeleniumHub()) || GlobalConfig.getInstance().getSeleniumHub() == null)
//            return new FirefoxDriver(profile);
            return new FirefoxDriver(DesiredCapabilities.firefox());
        else {
//            DesiredCapabilities capabilities = BrowserType.FIREFOX.getCapabilities();
//            capabilities.setCapability(FirefoxDriver.PROFILE, profile);
            try {
                return new RemoteWebDriver(new URL(GlobalConfig.getInstance().getSeleniumHub()), DesiredCapabilities.firefox());
            } catch (MalformedURLException e) {
                Logger.error(e.getMessage(), e);
                throw new RuntimeException(e.getMessage(), e);
//            	return new FirefoxDriver();
            }
        }
    }

    private WebDriver localChromeDriver() {
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("profile.default_content_settings.popups", 0);
//        prefs.put("download.prompt_for_download", "false");
//        prefs.put("download.default_directory", FileService.PATH_FOR_DOWNLOADING);
//        prefs.put("download.directory_upgrade", true);
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", prefs);
        if ("".equals(GlobalConfig.getInstance().getSeleniumHub()) || GlobalConfig.getInstance().getSeleniumHub() == null)
            return new ChromeDriver(DesiredCapabilities.chrome());
        else {
//            DesiredCapabilities capabilities = BrowserType.CHROME.getCapabilities();
//            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            try {
                return new RemoteWebDriver(new URL(GlobalConfig.getInstance().getSeleniumHub()), DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
            	Logger.error(e.getMessage(), e);
            	throw new RuntimeException(e.getMessage(), e);
//                return new ChromeDriver(options);
            }
        }
    }

    public WebDriver getWrappedDriver() {
        return driver;
    }

    public void open(String url) {
        Logger.debug("Open page : " + url);
        getWrappedDriver().get(url);
//        screenshot();
    }

    public void type(WebElement element, String text) {
        Logger.debug("Write text to : " + element.toString() + " text = '" + text + "'");
        waitForElementIsAppear(element, driver);
        element.sendKeys(text);
//        screenshot();
    }

    public void click(WebElement element) {
        Logger.debug("Click to : " + element.toString());
        waitForElementIsAppear(element, driver);
        element.click();
//        screenshot();
    }

    public String getText(WebElement element) {
        Logger.debug("Read text from : " + element.toString());
        waitForElementIsAppear(element, driver);
//        screenshot();
        return element.getText();
    }

    public void sendKeys(WebElement element, String key) {
        Logger.debug("Send key " + key + " to element " + element);
        waitForElementIsAppear(element, driver);
        element.sendKeys(key);
    }

    public void switchTo(WebElement element) {
        Logger.debug("Switch to: " + element.toString());
        waitForElementIsAppear(element, driver);
        driver.switchTo().frame(element);
//        screenshot();
    }

    public boolean isPresent(By locator) {
        Logger.debug("Is present element: " + locator.toString());
        WebElement element = driver.findElement(locator);
        waitForElementIsAppear(element, driver);
//        screenshot();
        return element.isEnabled();
    }

    public boolean isPresent(List<WebElement> elements, String fileName) {
        Logger.debug("Is present file with name: " + fileName);
        waitForElementIsClickable(elements.get(elements.size() % 2), driver);
        boolean result = false;
        for (WebElement file : elements) {
            if (file.getText().equals(fileName)) {
                result = true;
            }
        }
//        screenshot();
        return result;
    }

    public void refresh() {
        Logger.debug("Refreshing page");
        driver.navigate().refresh();
    }


    public String getAttribute(WebElement element, String attribute) {
        Logger.debug("Getting attribute " + attribute + " in" + element.toString());
        waitForElementIsAppear(element, driver);
//        screenshot();
        return element.getAttribute(attribute);
    }

    public void waitElement(WebElement element) {
        Logger.debug("Waiting for element:" + element.toString());
        waitForElementIsAppear(element, driver);
//        screenshot();
    }

    private static WebElement waitForElementIsAppear(WebElement element, WebDriver driver) {
        Logger.debug("Waiting for element appear: " + element.toString());
        new WebDriverWait(driver, COMMON_ELEMENT_WAIT_TIME_OUT).until(visibilityOf(element));
        new WebDriverWait(driver, COMMON_ELEMENT_WAIT_TIME_OUT).until(elementToBeClickable(element));
//        screenshot();
        return element;
    }

    private static WebElement waitForElementIsClickable(WebElement element, WebDriver driver) {
        Logger.debug("Waiting for element is clickable: " + element.toString());
        new WebDriverWait(driver, COMMON_ELEMENT_WAIT_TIME_OUT).until(elementToBeClickable(element));
//        screenshot();
        return element;
    }

//    public static void screenshot() {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//        File scrFile = ((TakesScreenshot) Browser.current().getWrappedDriver()).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(scrFile, new File(".\\screenshots", TestParameters.getTestName() + formater.format(calendar.getTime()) + ".png"));
//            Logger.info("Saving screenshot");
//        } catch (IOException e) {
//            Logger.error("Failed to write screenshot: " + e.getMessage(), e);
//            e.printStackTrace();
//        }
//    }
}
