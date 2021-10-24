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

    public void newCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.print("please enter the name of a customer ");
        String name = input.next();
        System.out.print("Please enter the customer's surname: ");
        String surname = input.next();
        System.out.print("What is the kwh rate for this customer? ");
        float kwh_rate = input.nextFloat();
        System.out.print("What is the customer's stand-by rate? ");
        float standby = input.nextFloat();

        regionsCustomers.add(new Customer(name, surname, kwh_rate, standby));
        numberOfCustomers++;
    }

    public String invoiceNumber() {
        String invoiceNumber;

        if (documentsIssued < 10) {
            invoiceNumber = String.format("IOE %05d", this.documentsIssued);
        } else if (documentsIssued < 100) {
            invoiceNumber = String.format("IOE %04d", this.documentsIssued);
        } else if (documentsIssued < 1000) {
            invoiceNumber = String.format("IOE %03d", this.documentsIssued);
        } else if (documentsIssued < 10000) {
            invoiceNumber = String.format("IOE %02d", this.documentsIssued);
        } else if (documentsIssued < 100000) {
            invoiceNumber = String.format("IOE %01d", this.documentsIssued);
        } else {
            invoiceNumber = String.format("IOE %d", this.documentsIssued);
        }

        return invoiceNumber;
    }

    public void createInvoice( Customer toBeCharged, Company invoiceIssuer)
            throws IOException {
        this.documentsIssued += 1;
        float KWH_charge = Math.round(toBeCharged.getKwhRate() * toBeCharged.getUsedSinceLastReading() * 100f) / 100f;
        float standby_charge = Math.round(toBeCharged.getStandbyRate() * toBeCharged.getUsedSinceLastReading() * 100f) / 100f;
        float TOTAL = Math.round((KWH_charge + standby_charge) * 100f) / 100f;

        String divider = "--------------------------------------------------";
        FileWriter fileWriter = new FileWriter(toBeCharged.getName() + toBeCharged.getSurname() + "_" + this.invoiceNumber() + "_IN");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(invoiceIssuer.getName() + "_" + this.getName());
        printWriter.println("");
        printWriter.println(divider);
        printWriter.println("");
        printWriter.println(String.format("Customer no.: %s", toBeCharged.getNumber()));
        printWriter.println(String.format("%45s %s", toBeCharged.getName(), toBeCharged.getSurname()));
        printWriter.println(String.format("%45s", toBeCharged.getAddress()));
        printWriter.println("");
        printWriter.println(divider);
        printWriter.println("");
        printWriter.println(String.format("Energy used as per last reading: %.2f", toBeCharged.getUsedSinceLastReading()));
        printWriter.println("");
        printWriter.println(divider);
        printWriter.println("");
        printWriter.println(String.format("rate per KWH: %.2f", toBeCharged.getKwhRate()));
        printWriter.println(String.format("standby rate per KWH: %.2f", toBeCharged.getStandbyRate()));
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println();
        printWriter.println();
        printWriter.println(String.format("KWH charge: %.2f", KWH_charge));
        printWriter.println(String.format("Standby charge: %.2f", standby_charge));
        printWriter.println(String.format("TOTAL: %.2f", TOTAL));
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println(String.format("account due: %.2f", toBeCharged.getAccount()));
        printWriter.println("Payment due: 30 days");
        printWriter.println();
        printWriter.println();
        printWriter.println();
        printWriter.println();
        printWriter.println(String.format("%45s", "_____________"));
        printWriter.println(String.format("%30s Regional Manager", new String("")));
        printWriter.close();
    }

    public void createReminder(Customer toBeReminded, Company issuer) throws IOException {
        this.documentsIssued += 1;
        String divider = "--------------------------------------------------";
        FileWriter fileWriter = new FileWriter(toBeReminded.getName() + toBeReminded.getSurname() + "_" + this.invoiceNumber() + "_R");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(issuer.getName() + "_" + this.getName());
        printWriter.println("");
        printWriter.println(divider);
        printWriter.println();
        printWriter.println(String.format("Customer no.: %s", toBeReminded.getNumber()));
        printWriter.println(String.format("%45s,%s", toBeReminded.getName(), toBeReminded.getSurname()));
        printWriter.println(String.format("%45s", toBeReminded.getAddress()));
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println("We would like to remind you about an outstanding account in place for you.");
        printWriter.println(String.format("The amount you owe us is %.2f.", toBeReminded.getAccount()));
        printWriter.println("We are sending this letter as a reminder, that your account has to be paid in full in the next 10 days.");
        printWriter.println("After this time you will incur the late fee of 2,5% per day.");
        printWriter.println();
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println();
        printWriter.println();
        printWriter.println(String.format("%45s", "_____________"));
        printWriter.println(String.format("%30s Regional Manager", new String("")));
        printWriter.close();

    }

    public void remainderWithLateFee(Customer toBeReminded, Company issuer) throws IOException {
        this.documentsIssued += 1;
        toBeReminded.setAccount( (toBeReminded.getAccount() + (toBeReminded.getAccount() * 0.025f)));
        String divider = "--------------------------------------------------";
        FileWriter fileWriter = new FileWriter(toBeReminded.getName() + toBeReminded.getSurname() + "_" + this.invoiceNumber() + "_RWF");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(issuer.getName() + "_" + this.getName());
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println(String.format("Customer no.: %s", toBeReminded.getNumber()));
        printWriter.println(String.format("%45s,%s", toBeReminded.getName(), toBeReminded.getSurname()));
        printWriter.println(String.format("%45s", toBeReminded.getAddress()));
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println("We would like to remind you about an outstanding account in place for you.");
        printWriter.println(String.format("The amount you owe us is %.2f.",toBeReminded.getAccount()));
        printWriter.println("We are sending this letter as a reminder, that your account has to be paid in full in the next 10 days.");
        printWriter.println("After this time you will incur the late fee of 2.5% per month.");
        printWriter.println();
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println();
        printWriter.println();
        printWriter.println(String.format("%45s", "_____________"));
        printWriter.println(String.format("%30s Regional Manager", new String("")));
        printWriter.close();

    }

    public void readingReminder(Customer toBeReminded, Company issuer) throws IOException {
        this.documentsIssued += 1;
        String divider = "--------------------------------------------------";
        FileWriter fileWriter = new FileWriter(toBeReminded.getName() + toBeReminded.getSurname() + "_" + this.invoiceNumber() + "_RR");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(issuer.getName() + "_" + this.getName());
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println(String.format("Customer no.: %s", toBeReminded.getNumber()));
        printWriter.println(String.format("%45s,%s", toBeReminded.getName(), toBeReminded.getSurname()));
        printWriter.println(String.format("%45s", toBeReminded.getAddress()));
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println("By this letter we would like to remind you, that a reading of your meter was last send to us more than 3 months ago.");
        printWriter.println("Please send us your reading, so that you can be charged accurately.");
        printWriter.println();
        printWriter.println();
        printWriter.println(divider);
        printWriter.println();
        printWriter.println();
        printWriter.println();
        printWriter.println(String.format("%45s", "_____________"));
        printWriter.println(String.format("%30s Regional Manager", new String("")));
        printWriter.close();

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
