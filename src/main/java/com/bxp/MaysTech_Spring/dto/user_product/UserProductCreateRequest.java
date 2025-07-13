package com.bxp.MaysTech_Spring.dto.user_product;

public class UserProductCreateRequest {
    private int userId;
    private int productId;

    public int getUsertId() {
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

}
