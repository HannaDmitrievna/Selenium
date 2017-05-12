package com.epam.selenium.framework.listeners;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.selenium.framework.reporting.Logger;
import com.epam.selenium.framework.ui.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportPortalListener extends ReportPortalTestNGListener {
    @Override
    public void onTestFailure(ITestResult tr) {
        Logger.error("Failed test: " +tr.getTestClass().getRealClass().getSimpleName() + "." + tr.getMethod().getMethodName(), tr.getThrowable());
        final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger("com.epam.selenium");
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", screenshot(), "Final state");
    }

    @Override
    public void onStart(ITestContext context) {
        Logger.info("Test " + context.getName() + " has been started");
        TestParameters.setTestName(context.getName());
        super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        Logger.info("Test " + context.getName() + " has been finished");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        Logger.info("Test " + tr.getTestName() + " skipped");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        Logger.info("Test " + tr.getTestName() + " finished with success");
    }
    @Override
    public void beforeConfiguration(ITestResult result) {
       onTestStart(result);
    }

    @Override
    public void onConfigurationSuccess(ITestResult result) {
        onTestSuccess(result);
    }

    @Override
    public void onConfigurationFailure(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onConfigurationSkip(ITestResult result) {
        onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public static String screenshot() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        File scrFile = ((TakesScreenshot) Browser.current().getWrappedDriver()).getScreenshotAs(OutputType.FILE);
        try {
            File file = new File(".\\screenshots", TestParameters.getTestName() + formater.format(calendar.getTime()) + ".png");
            FileUtils.copyFile(scrFile, file);
            Logger.info("Saving screenshot");
            return file.getAbsolutePath();
        } catch (IOException e) {
            Logger.error("Failed to write screenshot: " + e.getMessage(), e);
            e.printStackTrace();
            return "";
        }
    }
}