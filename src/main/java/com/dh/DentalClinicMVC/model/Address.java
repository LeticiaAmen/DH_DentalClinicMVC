package com.dh.DentalClinicMVC.model;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private Integer number;

    @Column(name = "location")
    private String location;

    @Column(name = "province")
    private String province;

    public Address() {
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public String getProvince() {
        return province;
    }
}
