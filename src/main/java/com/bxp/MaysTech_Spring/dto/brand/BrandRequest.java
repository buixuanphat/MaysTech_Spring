package com.bxp.MaysTech_Spring.dto.brand;

public class BrandRequest {
    String name;

    public BrandRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
