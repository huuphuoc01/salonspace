package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.AuthReponse;
import com.example.salonmanage.DTO.AuthRequest;
import com.example.salonmanage.Entities.User;
import com.example.salonmanage.JWT.JwtTokenUtil;
import com.example.salonmanage.service.FileStorageService;
import com.example.salonmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class AuthApi {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    UserService userService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getPhone(), request.getPassword())
            );
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthReponse response = new AuthReponse(user.getPhone(), user.getName(), user.getImg(), accessToken, user.getBirthday(), user.getEmail());
            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/auth/update")
    public ResponseEntity<?> update(@RequestBody @Valid AuthReponse request) {

        User user = userService.findByPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setName(request.getName());
        User newUser = userService.update(user);
        AuthReponse response = new AuthReponse(newUser.getPhone(), newUser.getName(), newUser.getImg(), request.getAccessToken(), newUser.getBirthday(), newUser.getEmail());
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/auth/updateImg")
    public ResponseEntity<?> updateImg(@RequestBody @Valid AuthReponse request) {

        User user = userService.findByPhone(request.getPhone());
        String[] pathSegments = user.getImg().split("/");
        fileStorageService.removeFile(pathSegments[pathSegments.length - 1]);
        LocalDateTime currentDateTime = LocalDateTime.now();
        String dateTimeString = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String[] strings = request.getImg().split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String uniqueImageName = UUID.randomUUID().toString();
        String pathfile = uniqueImageName+dateTimeString + "." + extension;
        fileStorageService.storeFile(new ByteArrayInputStream(data), pathfile);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/img/")
                .path(pathfile)
                .toUriString();
        user.setImg(fileDownloadUri);
        User newUser = userService.update(user);
        AuthReponse response = new AuthReponse(newUser.getPhone(), newUser.getName(), newUser.getImg(), request.getAccessToken(), newUser.getBirthday(), newUser.getEmail());
        return ResponseEntity.ok().body(response);
    }
}
