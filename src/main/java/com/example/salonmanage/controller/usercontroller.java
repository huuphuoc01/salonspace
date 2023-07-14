package com.example.salonmanage.controller;

import com.example.salonmanage.Entities.user;
import com.example.salonmanage.Entities.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.salonmanage.service.userService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class usercontroller {
    @Autowired
 private userService userService;
    @CrossOrigin(origins = "*")
    @PostMapping("/list")
public ResponseEntity<List<user>> getal(@RequestBody product user){
        List<com.example.salonmanage.Entities.user> list = userService.getAll();
        System.out.println(user.getId());
        System.out.println(user.getName());

    return ResponseEntity.ok().body(list);
}
}
