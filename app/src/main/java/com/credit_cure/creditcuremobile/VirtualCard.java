package com.credit_cure.creditcuremobile;

public class VirtualCard {
    private String cardNumber;
    private String amount;
    private String date;
    private String cvv;

    public VirtualCard(String cardNumber, String amount, String date, String cvv) {
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.date = date;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
