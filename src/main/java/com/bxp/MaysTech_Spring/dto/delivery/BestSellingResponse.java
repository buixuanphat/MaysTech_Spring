package com.bxp.MaysTech_Spring.dto.delivery;

public class BestSellingResponse {
    private Integer productId;
    private Long soLuongBan;
    private String productImage;

    public BestSellingResponse(Integer productId, Long soLuongBan) {
        this.productId = productId;
        this.soLuongBan = soLuongBan;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Long getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(Long soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
