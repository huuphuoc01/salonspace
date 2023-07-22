package com.example.salonmanage.service;

import com.example.salonmanage.DTO.registerDTO;
import com.example.salonmanage.Entities.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public User save(User user);
    public  void sendmail();


    public User findByPhone(String phone);
    public User update(User user);

    public String OTP (registerDTO registerDTO);

}
