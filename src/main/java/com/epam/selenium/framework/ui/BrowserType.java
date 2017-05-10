package com.epam.selenium.framework.ui;

import org.openqa.selenium.remote.DesiredCapabilities;

public enum BrowserType {
    CHROME("chrome"){
        @Override
        public DesiredCapabilities getCapabilities(){
            DesiredCapabilities capabilities=DesiredCapabilities.chrome();
            return capabilities;
        }
    },

    FIREFOX("firefox") {
        @Override
        public DesiredCapabilities getCapabilities(){
            DesiredCapabilities capabilities=DesiredCapabilities.firefox();
            return capabilities;
        }
    };

    private final String browserAlias;

    BrowserType(String browserAlias) { this.browserAlias = browserAlias; }

    public static BrowserType getByName(String value){
        for (BrowserType type : BrowserType.values()) {
            if(type.browserAlias.equals(value)){
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Incorrect alias " +value+" of browser type"));
    }

    public abstract DesiredCapabilities getCapabilities();
}
