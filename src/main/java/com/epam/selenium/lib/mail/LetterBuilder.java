package com.epam.selenium.lib.mail;

import com.epam.selenium.framework.utils.Randomizer;
import com.epam.selenium.lib.common.CommonConstants;

public class LetterBuilder {
    public static Letter getLetter() {
        Letter letter = new Letter();
        letter.setAddress(CommonConstants.DEFAULT_MAIL_TO_SEND);
        letter.setSubject("Letter subject: " + Randomizer.alphabetic());
        letter.setPost("Letter post:" + Randomizer.alphabetic());
        return letter;
    }
}
