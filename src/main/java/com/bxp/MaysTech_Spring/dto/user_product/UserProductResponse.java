package com.bxp.MaysTech_Spring.dto.user_product;

public class UserProductResponse {
    private Integer id;
    private int userId;
    private int productId;
    private Integer amount;
    private boolean isChosen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int cartId) {
        this.userId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
