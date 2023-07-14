package com.example.salonmanage.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="Service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    @NotNull
    private String name;


    private float price;

    private  String img;

    @ManyToOne()
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @OneToMany(mappedBy = "ImgDetail")
    private Collection<ImgDetail> imgDetails;

    @OneToMany(mappedBy = "comment")
    private Collection<comment> comments ;

    public Service() {
    }

    public Service(Integer id, @NotNull String name, float price, String img, Branch branch, Collection<ImgDetail> imgDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.branch = branch;
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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
                ", branch=" + branch +
                ", imgDetails=" + imgDetails +
                '}';
    }
}
