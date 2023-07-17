package com.example.salonmanage.Entities;

import javax.persistence.*;

@Entity
@Table(name = "booking_detail")
public class BookingDetail {
    @Id
    @GeneratedValue
    private long ID;
    @ManyToOne
    @JoinColumn(name="booking_id")
    private Booking booking;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

    public BookingDetail() {
    }

    public BookingDetail(long ID, Booking booking, Service service, com.example.salonmanage.Entities.User user) {
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

    public com.example.salonmanage.Entities.User getUser() {
        return user;
    }

    public void setUser(com.example.salonmanage.Entities.User user) {
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
