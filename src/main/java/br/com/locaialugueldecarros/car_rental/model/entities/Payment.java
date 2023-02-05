package br.com.locaialugueldecarros.car_rental.model.entities;

import br.com.locaialugueldecarros.car_rental.model.enums.PaymentMethod;

public class Payment {

    private int paymentId;
    private double amount;
    private PaymentMethod method;

    public Payment() {
    }

    public Payment(int paymentId, double amount, PaymentMethod method) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }
}
