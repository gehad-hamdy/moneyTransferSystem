package test;

import model.Account;
import service.AccountManager;

import java.util.Map;

public class AccountManagerTest {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager(Map.of(
            "Mark", new Account("Mark", 100),
            "Jane", new Account("Jane", 50),
            "Adam", new Account("Adam", 0)
        ));

        System.out.println(accountManager.transfer("Mark", "Jane", 30));
        System.out.println("Mark: " + accountManager.getBalance("Mark"));
        System.out.println("Jane: " + accountManager.getBalance("Jane"));
    }
}
