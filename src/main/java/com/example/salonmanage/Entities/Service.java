package com.example.salonmanage.Entities;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

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
    @Nationalized
    private String name;


    private float price;

    private  String img;
    @Nationalized
    private String description;

    @ColumnDefault("1")
    private int status;
    @OneToMany(mappedBy = "service")
    @JsonManagedReference
    private Collection<ImgDetail> imgDetails;

    @OneToMany(mappedBy = "service")
    private Collection<comment> comments ;

    public Service() {
    }

    public Service(Integer id, String name, float price, String img, String description, int status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.description = description;
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Collection<ImgDetail> getImgDetails() {
        return imgDetails;
    }

    public void setImgDetails(Collection<ImgDetail> imgDetails) {
        this.imgDetails = imgDetails;
    }

    public Collection<comment> getComments() {
        return comments;
    }

    public void setComments(Collection<comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", imgDetails=" + imgDetails +
                ", description=" + description +
                ", status=" + status +
                '}';
    }
}
