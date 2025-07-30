package com.bxp.MaysTech_Spring.dto.rating;

public class CreateRatingRequest {
    private int userId;
    private int productId;
    private int rating;

    public CreateRatingRequest(int userId, int productId, int rating) {
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
