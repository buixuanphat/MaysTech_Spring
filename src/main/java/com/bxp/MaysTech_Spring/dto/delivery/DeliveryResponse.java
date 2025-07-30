package com.bxp.MaysTech_Spring.dto.delivery;

import java.time.LocalDate;
import java.util.Date;

public class DeliveryResponse {
    private int id;
    private int userId;
    private LocalDate startDate;
    private LocalDate receivedDate;
    private String status;
    private boolean cancellationRequest;
    private double totalPrice;
    private int totalAmount;
    private double totalWeight;
    private boolean hasFeedback;

    public boolean isHasFeedback() {
        return hasFeedback;
    }

    public void setHasFeedback(boolean hasFeedback) {
        this.hasFeedback = hasFeedback;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCancellationRequest() {
        return cancellationRequest;
    }

    public void setCancellationRequest(boolean cancellationRequest) {
        this.cancellationRequest = cancellationRequest;
    }
}
