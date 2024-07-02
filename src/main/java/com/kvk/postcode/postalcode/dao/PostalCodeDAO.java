package com.kvk.postcode.postalcode.dao;

public class PostalCodeDAO {
    private String countryName;
    private String format;
    private String regex;

    public String getCountryName() {
        return countryName;
    }

    public PostalCodeDAO() {
    }

    public PostalCodeDAO(String countryName, String format, String regex) {
        this.countryName = countryName;
        this.format = format;
        this.regex = regex;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
