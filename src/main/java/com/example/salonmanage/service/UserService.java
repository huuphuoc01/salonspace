package com.example.salonmanage.service;

import com.example.salonmanage.DTO.registerDTO;
import com.example.salonmanage.Entities.User;

import java.util.List;

public interface UserService {
    public List<User> getAll();
    public User save(User user);
    public  void sendmail();
<<<<<<< HEAD
    public String OTP (String mail);
    public void register(registerDTO registerDTO);
=======


    public User findByPhone(String phone);
    public User update(User user);

    public String OTP (registerDTO registerDTO);

>>>>>>> f11ef166f8e066d41db0a8cc1887db0189a30969
}
