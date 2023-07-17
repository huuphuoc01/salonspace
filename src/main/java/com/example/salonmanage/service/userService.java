package com.example.salonmanage.service;

import com.example.salonmanage.Entities.User;

import java.util.List;

public interface userService {
    public List<User> getAll();
    public User save(User user);
}
