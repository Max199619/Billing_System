package com.company;

import java.io.*;
import java.util.*;

public class Customer {

    //class variables
    public static int NEXT_NUMBER = 999998;
    //instance variables
    private  String name;
    private  String surname;
    private String address;
    private String number;
    private float usedSinceLastReading;
    private float kwhRate;
    private float standbyRate;
    private float account;
    private float reading;

    public Customer(String c_name, String c_surname, float kwh_rate, float standby)
    {
        this.name = c_name;
        this.surname = c_surname;
        this.number = newNumber();
        this.usedSinceLastReading = 0.00f;
        this.kwhRate = kwh_rate;
        this.standbyRate = standby;
        this.account = 0.00f;
        this.reading = 0.00f;
        NEXT_NUMBER--;
    }

    public String newNumber()
    {
        int someNumber = 999999 - NEXT_NUMBER;
        String value;

        if (someNumber < 10)
        {
            value = String.format("CS%05d", someNumber);
        }
        else if (someNumber < 100)
        {
            value = String.format("CS%04d", someNumber);
        }
        else if (someNumber <1000)
        {
            value = String.format("CS%03d",someNumber);
        }
        else if (someNumber < 10000)
        {
            value = String.format("CS%02d", someNumber);
        }
        else if (someNumber < 100000)
        {
            value = String.format("CS%01d", someNumber);
        }
        else
        {
            value = String.format("CS%d", someNumber);
        }
        return value;
    }

    public void setUsedSinceLastReading(float usedSince) {
        this.usedSinceLastReading = usedSince;
    }

    public float getUsedSinceLastReading() {
        return usedSinceLastReading;
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    public float getKwhRate() {
        return kwhRate;
    }

    public float getStandbyRate() {
        return standbyRate;
    }

    public float getAccount() {
        return account;
    }

    public void setAccount(float account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public float getReading() {
        return reading;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setKwhRate(float kwhRate) {
        this.kwhRate = kwhRate;
    }

    public void setReading(float reading) {
        this.reading = reading;
    }

    public void setStandbyRate(float standbyRate) {
        this.standbyRate = standbyRate;
    }
}


