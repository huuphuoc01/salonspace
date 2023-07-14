package com.example.salonmanage.Entities;

import jakarta.persistence.*;

import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name="rank")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "user")
    private Collection<user> users;

    public Rank() {
    }

    public Rank(int id, Collection<user> users) {
        this.id = id;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<user> getUsers() {
        return users;
    }

    public void setUsers(Collection<user> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", users=" + users +
                '}';
    }
}
