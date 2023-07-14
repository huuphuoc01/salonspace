package com.example.salonmanage.Entities;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "Event")
public class event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date startDay;
    private Date endDay;
    private float discount;
    private String content;
    private String title;
    @OneToMany(mappedBy = "rankID")
    private Collection<Rank> ranks;

    public event() {
    }

    public event(int id, Date startDay, Date endDay, float discount, String content, String title, Collection<Rank> ranks) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.discount = discount;
        this.content = content;
        this.title = title;
        this.ranks = ranks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Rank> getRanks() {
        return ranks;
    }

    public void setRanks(Collection<Rank> ranks) {
        this.ranks = ranks;
    }

    @Override
    public String toString() {
        return "event{" +
                "id=" + id +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", discount=" + discount +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", ranks=" + ranks +
                '}';
    }
}
