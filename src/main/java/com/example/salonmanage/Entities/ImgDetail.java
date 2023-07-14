package com.example.salonmanage.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ImgDeatail")
public class ImgDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String img;
    @ManyToOne
    @JoinColumn(name = "serviceID")
    private Service service;

    public ImgDetail() {
    }

    public ImgDetail(int id, String img, Service service) {
        this.id = id;
        this.img = img;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ImgDetail{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", service=" + service +
                '}';
    }
}

