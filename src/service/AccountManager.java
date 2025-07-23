package service;

import model.Account;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountManager {
    private final Map<String, Account> accounts;

    public AccountManager(Map<String, Account> initialAccounts) {
        this.accounts = new ConcurrentHashMap<>(initialAccounts);
    }

    // public AccountManager() {
    //     accounts.put("Mark", new Account("Mark", 100));
    //     accounts.put("Jane", new Account("Jane", 50));
    //     accounts.put("Adam", new Account("Adam", 0));
    // }

    public String transfer(String from, String to, int amount) {
        if (from.equals(to))
            return "Transfer failed: sender and receiver cannot be the same.";

        Account sender = accounts.get(from);
        Account receiver = accounts.get(to);

        if (sender == null || receiver == null)
            return "Transfer failed: one or both users are invalid.";

        Account first = from.compareTo(to) < 0 ? sender : receiver;
        Account second = from.compareTo(to) < 0 ? receiver : sender;

        first.getLock().lock();
        second.getLock().lock();
        try {
            if (sender.getBalance() < amount) {
                return String.format("Transfer failed: %s has insufficient funds ($%d requested, $%d available).",
                    sender.getName(), amount, sender.getBalance());
            }

            sender.withdraw(amount);
            receiver.deposit(amount);

            return String.format("Success: $%d transferred from %s to %s.", amount, from, to);
        } finally {
            second.getLock().unlock();
            first.getLock().unlock();
        }
    }

    public String getBalances() {
        StringBuilder sb = new StringBuilder();
        for (Account acc : accounts.values()) {
            sb.append(acc.getName()).append(" has $").append(acc.getBalance()).append("\n");
        }
        return sb.toString();
    }

    public int getBalance(String name) {
        Account account = accounts.get(name);
        if (account == null) {
            throw new IllegalArgumentException("No such account: " + name);
        }
        return account.getBalance();
    }
}
