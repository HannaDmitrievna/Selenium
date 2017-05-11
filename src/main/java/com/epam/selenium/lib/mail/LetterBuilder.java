package com.epam.selenium.lib.mail;

import com.epam.selenium.framework.utils.Randomizer;

public class LetterBuilder {
    public static Letter getLetter() {
        Letter letter = new Letter();
        letter.setAddress(System.getProperty("mail.to.send"));
        letter.setSubject("Letter subject: " + Randomizer.alphabetic());
        letter.setPost("Letter post:" + Randomizer.alphabetic());
        return letter;
    }
}
