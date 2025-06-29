package test1;

import java.util.*;


public class Account implements AccountService {

    private final List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    @Override
    public void deposit(int amount, String date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

    @Override
    public void withdraw(int amount, String date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("DATE       | AMOUNT | BALANCE");
        List<Transaction> reversed = new ArrayList<>(transactions);
        Collections.reverse(reversed);
        for (Transaction t : reversed) {
            System.out.printf("%s | %d | %d%n", t.date, t.amount, t.balance);
        }
    }
}
