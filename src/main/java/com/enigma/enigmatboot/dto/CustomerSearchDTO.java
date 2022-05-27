package com.enigma.enigmatboot.dto;

import java.util.Date;

public class CustomerSearchDTO {
    private String searchCustomerFirstName;
    private String searchCustomerLastName;
    private Date searchCustomerDateOfBirth;

    public CustomerSearchDTO(String searchCustomerFirstName, String searchCustomerLastName, Date seacrhCustomerDateOfBirth) {
        this.searchCustomerFirstName = searchCustomerFirstName;
        this.searchCustomerLastName = searchCustomerLastName;
        this.searchCustomerDateOfBirth = seacrhCustomerDateOfBirth;
    }

    public String getSearchCustomerFirstName() {
        return searchCustomerFirstName;
    }

    public void setSearchCustomerFirstName(String searchCustomerFirstName) {
        this.searchCustomerFirstName = searchCustomerFirstName;
    }

    public String getSearchCustomerLastName() {
        return searchCustomerLastName;
    }

    public void setSearchCustomerLastName(String searchCustomerLastName) {
        this.searchCustomerLastName = searchCustomerLastName;
    }

    public Date getSearchCustomerDateOfBirth() {
        return searchCustomerDateOfBirth;
    }

    public void setSearchCustomerDateOfBirth(Date searchCustomerDateOfBirth) {
        this.searchCustomerDateOfBirth = searchCustomerDateOfBirth;
    }
}
