package com.example.salonmanage.Entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Collection;

@Entity
@Table(name="service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    @NotNull
    private String name;


    private float price;

    private  String img;



    @OneToMany(mappedBy = "service")
    private Collection<ImgDetail> imgDetails;

    @OneToMany(mappedBy = "service")
    private Collection<comment> comments ;

    public Service() {
    }

    public Service(Integer id, @NotNull String name, float price, String img, Branch branch, Collection<ImgDetail> imgDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;

        this.imgDetails = imgDetails;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



    public Collection<ImgDetail> getImgDetails() {
        return imgDetails;
    }

    public void setImgDetails(Collection<ImgDetail> imgDetails) {
        this.imgDetails = imgDetails;
    }

    @Override
    public String toString() {
        return "service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", imgDetails=" + imgDetails +
                '}';
    }
}
