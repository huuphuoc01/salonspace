package com.example.salonmanage.service;

import com.example.salonmanage.Entities.user;
import com.example.salonmanage.reponsitory.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
   @Autowired
    private userRepository userRepository;

    @Override
    public List<user> getAll() {
        System.out.println(userRepository.findAll());
        return (List<user>) userRepository.findAll();
    }
}
