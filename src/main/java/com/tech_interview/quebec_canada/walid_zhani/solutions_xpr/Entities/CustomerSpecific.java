package com.tech_interview.quebec_canada.walid_zhani.solutions_xpr.Entities;

public class CustomerSpecific {
    boolean foundCustomer;
    private int idCustomer;
    private String firstName;
    private String lastName;

    public CustomerSpecific(boolean foundCustomer, int idCustomer, String firstName, String lastName) {
        this.foundCustomer = foundCustomer;
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerSpecific(boolean foundCustomer, String firstName, String lastName) {
        this.foundCustomer = foundCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerSpecific(boolean foundCustomer) {
        this.foundCustomer = foundCustomer;
    }

    public boolean isFoundCustomer() {
        return foundCustomer;
    }

    public void setFoundCustomer(boolean foundCustomer) {
        this.foundCustomer = foundCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Override
    public String toString() {
        return "CustomerSpecific{" +
                "foundCustomer=" + foundCustomer +
                ", idCustomer=" + idCustomer +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}