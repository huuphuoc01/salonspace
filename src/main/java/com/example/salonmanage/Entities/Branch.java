package com.example.salonmanage.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name="branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    @NotNull
    @Length(min = 5, max = 128)
    private String name;

    private double lat;

    private double lng;

    @Column(nullable = false, length = 1028)
    @NotNull
    private String address;




    public Branch() {

    }

    public Branch(Integer id, @NotNull String name, double lat, double lng, @NotNull String address) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", address='" + address + '\'' +
                '}';
    }
}
