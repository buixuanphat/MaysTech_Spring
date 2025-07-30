package com.bxp.MaysTech_Spring.dto.rating;

public class RatingResponse {
    private int id;
    private int userId;
    private int productId;
    private int rating;

    public RatingResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RatingResponse(int id, int userId, int productId, int rating) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
