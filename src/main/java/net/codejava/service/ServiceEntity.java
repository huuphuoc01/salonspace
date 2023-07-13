package net.codejava.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.codejava.branch.BranchEnity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "service")
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 128)
    @NotNull
    @Length(min = 5, max = 128)
    private String name;


    private float price;

    private  String img;

    @ManyToOne()
    @JoinColumn(name = "branch_id")
    private BranchEnity branch;

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

    public BranchEnity getBranch() {
        return branch;
    }

    public void setBranch(BranchEnity branch) {
        this.branch = branch;
    }

    public ServiceEntity(Integer id, String name, float price, String img, BranchEnity branch) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.branch = branch;
    }

    public  ServiceEntity(){

    }
}
