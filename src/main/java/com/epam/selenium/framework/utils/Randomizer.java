package com.epam.selenium.framework.utils;


import org.apache.commons.lang3.RandomStringUtils;

public class Randomizer {
    
    private static final int DEFAULT_LENGTH = 20;

    public static String alphabetic() {
        return RandomStringUtils.randomAlphabetic(DEFAULT_LENGTH);
    }
}
