package com.techelevator;
class CreditCardAccount implements Accountable {
    private String accountHolder;
    private String accountNumber;
    private int debt;

    public CreditCardAccount(String accountHolder, String accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.debt = 0;
    }
    public int getBalance() {
        return -debt; // Debt represented as a negative balance.
    }

    public int pay(int amountToPay) {
        if (amountToPay <= 0) {
            return debt;
        }
        debt = Math.max(0, debt - amountToPay);
        return debt;
    }

    public int charge(int amountToCharge) {
        if (amountToCharge <= 0) {
            return debt;
        }
        debt += amountToCharge;
        return debt;
    }
   //getters and setters for accountholder and account number

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
