package com.example.salonmanage.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "booking_detail")
public class BookingDetail {
    @Id
    @GeneratedValue
    private Integer ID;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="booking_id")
    private Booking booking;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "service_id")
    private Service service;
    @Column
    private Integer cus_id;
    @Column
    private Integer status;

    public BookingDetail() {
    }

    public BookingDetail(Integer ID, Booking booking, Service service, Integer cus_id, Integer status) {
        this.ID = ID;
        this.booking = booking;
        this.service = service;
        this.cus_id = cus_id;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getCus_id() {
        return cus_id;
    }

    public void setCus_id(Integer cus_id) {
        this.cus_id = cus_id;
    }

    @Override
    public String toString() {
        return "BookingDetail{" +
                "ID=" + ID +
                ", booking=" + booking +
                ", service=" + service +
                ", cus_id=" + cus_id +
                ", status=" + status +
                '}';
    }
}
