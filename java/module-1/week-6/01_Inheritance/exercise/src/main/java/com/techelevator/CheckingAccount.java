package com.techelevator;

public class CheckingAccount extends BankAccount {
    public CheckingAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName,accountNumber);

    }
    public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
       super(accountHolderName, accountNumber, balance);
    }

    @Override
    public int withdraw(int amountToWithdraw) {
        if (getBalance() >= amountToWithdraw) {
            if (getBalance() < 0 && getBalance() >= -100) {
            super.withdraw(amountToWithdraw + 10);
            } else {
              super.withdraw(amountToWithdraw);
            }
        }
        return getBalance();
    }
}
