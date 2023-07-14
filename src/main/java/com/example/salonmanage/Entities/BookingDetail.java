package com.example.salonmanage.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "BookingDetail")
public class BookingDetail {
    @Id
    @GeneratedValue
    private long ID;
    @ManyToOne
    @JoinColumn(name="bookingID")
    private Booking booking;
    @ManyToOne
    @JoinColumn(name = "serviceID")
    private Service service;
    @ManyToOne()
    @JoinColumn(name="userID")
    private user user;

    public BookingDetail() {
    }

    public BookingDetail(long ID, Booking booking, Service service, com.example.salonmanage.Entities.user user) {
        this.ID = ID;
        this.booking = booking;
        this.service = service;
        this.user = user;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
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

    public com.example.salonmanage.Entities.user getUser() {
        return user;
    }

    public void setUser(com.example.salonmanage.Entities.user user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BookingDetail{" +
                "ID=" + ID +
                ", booking=" + booking +
                ", service=" + service +
                ", user=" + user +
                '}';
    }
}
