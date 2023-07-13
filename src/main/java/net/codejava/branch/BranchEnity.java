package net.codejava.branch;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.codejava.service.ServiceEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "branch")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class BranchEnity {
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


    @OneToMany(mappedBy = "branch")
    private Collection<ServiceEntity> service;

    public BranchEnity(String name, float lat, float lng, String address, Collection<ServiceEntity> service) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.service = service;
    }

    public BranchEnity() {

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
}
