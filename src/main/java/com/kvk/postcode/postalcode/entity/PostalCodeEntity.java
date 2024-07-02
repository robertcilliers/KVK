package com.kvk.postcode.postalcode.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "postal_code")
public class PostalCodeEntity {
    @Id
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name= "format")
    private String format;
    @Basic
    @Column(name = "regex")
    private String regex;

    @Basic
    @Column(name = "country_name")
    private String countryName;


    public PostalCodeEntity() {
    }

    public PostalCodeEntity(String code, String format, String regex, String countryName) {
        this.code = code;
        this.format = format;
        this.regex = regex;
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
