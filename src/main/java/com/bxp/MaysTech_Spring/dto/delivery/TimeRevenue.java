package com.bxp.MaysTech_Spring.dto.delivery;

public class TimeRevenue {
    String time;
    double revenue;

    public TimeRevenue(String time, double revenue) {
        this.time = time;
        this.revenue = revenue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
