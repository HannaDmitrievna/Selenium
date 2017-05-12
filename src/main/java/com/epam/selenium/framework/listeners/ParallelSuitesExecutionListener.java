package com.epam.selenium.framework.listeners;

import com.epam.selenium.framework.config.GlobalConfig;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class ParallelSuitesExecutionListener implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
        suite.getXmlSuite().setParallel(GlobalConfig.getInstance().getParallelMode());
        suite.getXmlSuite().setThreadCount(GlobalConfig.getInstance().getThreadCount());
    }

    @Override
    public void onFinish(ISuite suite) {
        // do nothing
    }
}
