package com.bxp.MaysTech_Spring.dto.user_product;

public class UserProductTotalResponse {

    private int totalAmount;
    private double totalPrice;

    public UserProductTotalResponse(int totalAmount, double totalPrice) {
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
    }

    public UserProductTotalResponse() {
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

