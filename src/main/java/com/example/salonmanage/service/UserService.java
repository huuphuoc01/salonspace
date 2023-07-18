package com.example.salonmanage.service;

import com.example.salonmanage.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    public List<User> getAll();
    public User save(User user);
}
