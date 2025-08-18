package com.bxp.MaysTech_Spring.dto.delivery;

public class ProductRevenue {
    int id;
    String name;
    double revenue;

    public ProductRevenue(int id, String name, double revenue) {
        this.id = id;
        this.name = name;
        this.revenue = revenue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
