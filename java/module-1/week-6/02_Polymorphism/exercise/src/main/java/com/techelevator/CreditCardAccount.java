package com.techelevator;

   public class CreditCardAccount implements Accountable {
    private String accountHolder;
    private String accountNumber;
    private int debt;

    public CreditCardAccount(String accountHolder, String accountNumber){
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.debt = 0;

    }
    public int pay(int amountToPay) {
        int newDebt = Math.max(debt - amountToPay, 0);
        int paymentAmount = debt - newDebt;
        debt = newDebt;
        return paymentAmount;

    }

    public int charge(int amountToCharge) {
        debt +=amountToCharge;
        return debt;
    }
    @Override
    public int getBalance(){
        return -debt;
    }
}
