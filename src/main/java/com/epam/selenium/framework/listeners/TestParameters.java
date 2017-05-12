package com.epam.selenium.framework.listeners;

public class TestParameters {
    private static String testName;

    public static String getTestName() {
        return testName;
    }

    public static void setTestName(String name) {
        TestParameters.testName = name;
    }
}
