package com.credit_cure.creditcuremobile;

import java.io.Serializable;

public class VirtualCard implements Serializable{
    public String cardNumber;
    public String amount;
    public String date;
    public String cvv;

    public VirtualCard() {

    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        VirtualCard vc = (VirtualCard) obj;

        return vc.getCardNumber().equals(this.cardNumber);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String id;

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
