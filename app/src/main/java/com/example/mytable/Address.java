package com.example.mytable;

public class Address { // represents an Address and contains street, street number, and postal code

    private String street, postalCode, city, province, country;

    private int number, unit;

    public Address() { // constructor for Address without parameters

    }

    public Address(String street, String postalCode, String city, String province, String country, int number, int unit) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
        this.country = country;
        this.number = number;
        this.unit = unit;
    }

    public Address(String street, String postalCode, String city, String province, String country, int number) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
        this.country = country;
        this.number = number;
        this.unit = 0;
    }



    public String getStreet() {
        return street;
    }

    public boolean setStreet(String street) {
        if (street.equals("")) {
            return(false);
        } else {
            this.street = street;
            return(true);
        }
    }

    public String getPostalCode() {
        return postalCode;
    }

    public boolean setPostalCode(String postalCode) {
        if (postalCode.equals("")) {
            return(false);
        } else {
            this.postalCode = postalCode;
            return(true);
        }
    }

    public String getCity() {
        return city;
    }

    public boolean setCity(String city) {
        if (city.equals("")) {
            return(false);
        } else {
            this.city = city;
            return(true);
        }
    }

    public String getProvince() {
        return province;
    }

    public boolean setProvince(String province) {
        if (province.equals("")) {
            return(false);
        } else {
            this.province = province;
            return(true);
        }
    }

    public String getCountry() {
        return country;
    }

    public boolean setCountry(String country) {
        if (country.equals("")) {
            return(false);
        } else {
            this.country = country;
            return(true);
        }
    }

    public int getNumber() {
        return number;
    }

    public boolean setNumber(String number) {
        if (number.equals("")) {
            return(false);
        } else {
            this.number = Integer.parseInt(number);
            return(true);
        }
    }

    public int getUnit() {
        return unit;
    }

    public boolean setUnit(String unit) {
        if (unit.equals("")) {
            return(false);
        } else {
            this.unit = Integer.parseInt(unit);
            return(true);
        }
    }
}
