package com.company;

import java.io.*;
import java.util.*;

public class Region {
    private int numberOfCustomers;
    private  String name;
    private final String code;
    private List<Customer> regionsCustomers;
    private int documentsIssued;
    private float intake;

    public Region(String r_name) {
        this.numberOfCustomers = 0;
        this.name = r_name;
        this.code = createRegionCode();
        this.regionsCustomers = new ArrayList<>();
        this.documentsIssued = 0;
        this.intake = 0.00f;
    }

    public String createRegionCode() {
        int ascii = 0;
        for (char character : name.toCharArray())
        {
            ascii += (int) character;
        }
        return Integer.toHexString(ascii);
    }

    public String getCode() {
        return code;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public String getName() {
        return name;
    }

    public void setDocumentsIssued(int documentsIssued) {
        this.documentsIssued = documentsIssued;
    }

    public void setRegionsCustomers(List<Customer> regionsCustomers) {
        this.regionsCustomers = regionsCustomers;
    }

    public float getIntake() {
        return intake;
    }

    public int getDocumentsIssued() {
        return documentsIssued;
    }

    public List<Customer> getRegionsCustomers() {
        return regionsCustomers;
    }


    public void customerPayment(String c_name, float payment)
    {
        for(Customer customer: regionsCustomers)
        {
            if (c_name.equals(customer.getName()))
            {
                customer.setAccount(customer.getAccount() - payment);
                this.intake += payment;
            }
        }
    }



}
