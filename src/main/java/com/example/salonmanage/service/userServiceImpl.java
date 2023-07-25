package com.example.salonmanage.service;

import com.example.salonmanage.DTO.registerDTO;
import com.example.salonmanage.Entities.User;
import com.example.salonmanage.reponsitory.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class userServiceImpl implements UserService {
    @Autowired
    private userRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @Override
    public void sendmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("salonspaseteam@gmail.com");
        message.setTo("hoangphucqh1234@gmail.com");
        message.setText("hellll000");
        message.setSubject("hêhehehe");
        mailSender.send(message);
    }

    @Override

    public User findByPhone(String phone){
        User user = userRepository.findByPhone(phone).get();
        return  user;
    }

    @Override
    public  User update(User user){
        return userRepository.save(user);
    }
    @Override
    public String OTP(registerDTO registerDTO) {
        int randomPIN = (int) (Math.random() * 9000) + 1000;
        String stringRandomPIN = String.valueOf(randomPIN);
        SimpleMailMessage message =new SimpleMailMessage();
        message.setFrom("salonspaseteam@gmail.com");
        message.setTo(registerDTO.getEmail());
        message.setText("Hello \n\n" +"Your Register OTP :" + stringRandomPIN + ".Please Verify. \n\n"+"Regards \n"+"SalonSpace");
        message.setSubject("Regiter OPT");
        mailSender.send(message);

        return stringRandomPIN;
    }
}




