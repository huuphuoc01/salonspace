package com.example.salonmanage.service;

import com.example.salonmanage.Entities.User;
import com.example.salonmanage.reponsitory.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class userServiceImpl implements UserService {
   @Autowired
    private userRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Override
    public List<User> getAll() {
        System.out.println(userRepository.findAll());
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User save(User user) {
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

      return userRepository.save(user);
    }
}
