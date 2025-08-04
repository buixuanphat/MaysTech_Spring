package com.bxp.MaysTech_Spring.dto.product_highlight;

import com.bxp.MaysTech_Spring.entity.Product;

public class ProductHighlightCreateRequest {

    private int productId;
    private String image;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
