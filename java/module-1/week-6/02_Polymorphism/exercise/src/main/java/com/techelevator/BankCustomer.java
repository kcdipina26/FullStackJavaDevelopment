package com.techelevator;
import java.util.ArrayList;
import java.util.List;

public class BankCustomer {
    private String name;
    private String address;
    private String phoneNumber;
    private List<Accountable> accounts;

    public BankCustomer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();

    }
   public Accountable[] getAccount() {
        return accounts.toArray(new Accountable[0]);
   }
   public void addAccount(Accountable newAccount) {
        accounts.add(newAccount);
   }
   public boolean isVip() {
        int totalBalance = 0;

        for (Accountable account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance >= 25000;
   }
}
