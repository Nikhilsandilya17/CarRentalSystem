package model;

public class Customer {
    private String name;
    private String driversLicenseNumber;
    private String phoneNumber;

    public Customer(String name, String driversLicenseNumber, String phoneNumber) {
        this.name = name;
        this.driversLicenseNumber = driversLicenseNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriversLicenseNumber() {
        return driversLicenseNumber;
    }

    public void setDriversLicenseNumber(String driversLicenseNumber) {
        this.driversLicenseNumber = driversLicenseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
