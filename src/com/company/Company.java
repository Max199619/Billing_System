package com.company;

import java.io.*;
import java.util.*;

public class Company {
    //variables of class Company
    private final String name;
    private float value;
    private float powerSold; //power sold in this month
    private float intake; //money taken this month
    private Map<String, Region> regions;
    private int numberOfRegions;

    public Company(String c_name, float c_value)
    {
        this.name = c_name;
        this.intake = 0.00f;
        this.value = c_value;
        this.regions = new HashMap<>();
        this.powerSold = 0.00f;
        this.numberOfRegions = 0;
    }

    public  String getName() {
        return name;
    }

    public Map<String, Region> getRegions() {
        return regions;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getIntake() {
        return intake;
    }

    public void setIntake(float intake) {
        this.intake = intake;
    }

    public float getPowerSold() {
        return powerSold;
    }

    public void setPowerSold(float powerSold) {
        this.powerSold = powerSold;
    }

    public void setRegions(Map<String, Region> regions) {
        this.regions = regions;
    }
}
