package com.bxp.MaysTech_Spring.dto.user_product;

public class UserProductTotalResponse {

    private int totalAmount;
    private double totalPrice;
    private double totalWeight;

    public UserProductTotalResponse(int totalAmount, double totalPrice, double totalWeight) {
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
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

