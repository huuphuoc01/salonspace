package com.example.salonmanage.Entities;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

@Entity
@Table(name = "user")
public class user {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
@Column(nullable = false)
private String name;
@Column(nullable = false)
private String birthday;
@Column(nullable = false)
private String phone;
@Column()
private String img;
@Column(nullable=false,length = 255)
private String password;
@Column(nullable = false)
private String email;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @ManyToOne()
    @JoinColumn(name="rankID")
    private Rank rank;
    @Column
    private int status;
    @ManyToOne()
    @JoinColumn(name="branchID")
    private Branch branch;

    @OneToMany(mappedBy = "comment")
    private List<comment> comments =new ArrayList();

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
//    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
