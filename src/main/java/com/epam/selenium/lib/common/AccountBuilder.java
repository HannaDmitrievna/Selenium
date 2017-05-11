package com.epam.selenium.lib.common;

import com.epam.selenium.framework.utils.Randomizer;

public class AccountBuilder {
    public static Account getDefaultAccount() {
        Account account = new Account();
        account.setLogin(System.getProperty("yandex.login"));
        account.setPassword(System.getProperty("yandex.password"));
        account.setEmail(System.getProperty("mail.to.send"));
        return account;
    }

    public static Account getAccountWithWrongPass() {
        Account account = getDefaultAccount();
        account.setLogin(account.getLogin());
        account.setPassword(account.getPassword() + Randomizer.alphabetic());
        return account;
    }

    public static Account getAccountWithWrongCred() {
        Account account = getDefaultAccount();
        account.setLogin(account.getLogin() + Randomizer.alphabetic());
        account.setPassword(account.getPassword() + Randomizer.alphabetic());
        return account;
    }
}
