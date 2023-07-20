package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.registerDTO;
import com.example.salonmanage.DTO.userDTO;
import com.example.salonmanage.Entities.User;
import com.example.salonmanage.reponsitory.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.salonmanage.service.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class usercontroller {
    @Autowired  private UserService userService;

    @Autowired
    private userRepository userRepository;
    private final Map<String, String> otpStore = new HashMap<>();

    @PutMapping()
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        User createdUser = userService.save(user);
        URI uri = URI.create("/users/" + createdUser.getId());
        ////ABC
        userDTO userDto = new userDTO(createdUser.getId(), createdUser.getPhone());

        return ResponseEntity.created(uri).body(userDto);
    }
    @PostMapping("/mail")
    public ResponseEntity<String> Otp(@RequestBody registerDTO registerDTO){
        boolean phoneExist = userRepository.existsByPhone(registerDTO.getPhone());
        boolean emailExist = userRepository.existsByEmail(registerDTO.getEmail());
        String email = registerDTO.getEmail();
        if(phoneExist == false){
            if (emailExist == false){
            String opt = userService.OTP(registerDTO);
                otpStore.put(email,opt);
            return ResponseEntity.ok("Generate OTP successfully");
            }
            else{
             return ResponseEntity.ok("Email is already existed");
            }
        }else {
            return ResponseEntity.ok("Phone is already existed");
        }
    }
}
