package com.techelevator;

public class SavingsAccount extends BankAccount{
    public SavingsAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }

    public SavingsAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public int withdraw(int amountToWithdraw) {
        int serviceCharge = 2;

        if (amountToWithdraw > 0) {
            int newBalance = super.getBalance() - amountToWithdraw;

            if (newBalance >= 150) {
                super.withdraw(amountToWithdraw);
            } else if (newBalance >= 0) {
                super.withdraw(amountToWithdraw + serviceCharge);
            }
        }
        return super.getBalance();
    }
}





