package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.userDTO;
import com.example.salonmanage.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.salonmanage.service.userService;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/users")
public class usercontroller {
    @Autowired  private userService userService;




    @PutMapping()
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        User createdUser = userService.save(user);
        URI uri = URI.create("/users/" + createdUser.getId());
        ////ABC
        userDTO userDto = new userDTO(createdUser.getId(), createdUser.getPhone());

        return ResponseEntity.created(uri).body(userDto);
    }
}
