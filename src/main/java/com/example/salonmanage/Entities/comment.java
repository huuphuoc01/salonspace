package com.example.salonmanage.Entities;

import javax.persistence.*;

@Entity
@Table(name="comment")
public class comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int parentID;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "service_id")
    private Service service;

    public comment() {
    }

    public comment(int id, int parentID, User user, Service service) {
        this.id = id;
        this.parentID = parentID;
        this.user = user;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "comment{" +
                "id=" + id +
                ", parentID=" + parentID +
                ", user=" + user +
                ", service=" + service +
                '}';
    }
}
