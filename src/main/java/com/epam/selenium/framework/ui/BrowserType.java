package com.epam.selenium.framework.ui;

import org.openqa.selenium.remote.DesiredCapabilities;

public enum BrowserType {
    CHROME("chrome") {
        @Override
        public DesiredCapabilities getCapabilities() {
            return DesiredCapabilities.chrome();
        }
    },

    FIREFOX("firefox") {
        @Override
        public DesiredCapabilities getCapabilities() {
            return DesiredCapabilities.firefox();
        }
    };

    private final String browserAlias;

    BrowserType(String browserAlias) {
        this.browserAlias = browserAlias;
    }

    public static BrowserType getByName(String value) {
        for (BrowserType type : BrowserType.values()) {
            if (type.browserAlias.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Incorrect alias %s of browser type", value));
    }

    public abstract DesiredCapabilities getCapabilities();
}
