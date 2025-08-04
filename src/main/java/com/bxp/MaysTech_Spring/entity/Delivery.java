package com.bxp.MaysTech_Spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "received_date")
    private LocalDate receivedDate;

    @NotNull
    @ColumnDefault("'PREPARING'")
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "cancellation_request", nullable = false)
    private Boolean cancellationRequest = false;

    @NotNull
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @NotNull
    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @ColumnDefault("0")
    @Column(name = "has_feedback")
    private Boolean hasFeedback;

    @Size(max = 100)
    @Column(name = "address", length = 100)
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Boolean getCancellationRequest() {
        return cancellationRequest;
    }

    public void setCancellationRequest(Boolean cancellationRequest) {
        this.cancellationRequest = cancellationRequest;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getHasFeedback() {
        return hasFeedback;
    }

    public void setHasFeedback(Boolean hasFeedback) {
        this.hasFeedback = hasFeedback;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}