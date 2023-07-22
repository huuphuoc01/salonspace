package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.ServiceDTO;
import com.example.salonmanage.Entities.Service;
import com.example.salonmanage.reponsitory.ServiceRepository;
import com.example.salonmanage.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "*")
public class ServiceApi {
    @Autowired
    private ServiceRepository serviceRepo;

    @Autowired
    private FileStorageService fileStorageService;
    private ResponseEntity<Service> body;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ServiceDTO service) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String dateTimeString = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String[] strings = service.getImg().split(",");
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
        String pathfile = dateTimeString + "." + extension;
        fileStorageService.storeFile(new ByteArrayInputStream(data), pathfile);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/img/")
                .path(pathfile)
                .toUriString();

        Service newService = new Service();
        newService.setName(service.getName());
        newService.setPrice(service.getPrice());
        newService.setImg(fileDownloadUri);
        Service savedService = serviceRepo.save(newService);
        URI productURI = URI.create("/service/" + savedService.getId());
        return body;
    }

    @GetMapping
    public List<Service> list() {
        return serviceRepo.findAll();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ServiceDTO service) {
        if (serviceRepo.existsById(service.getId()) == true) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String dateTimeString = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String[] strings = service.getImg().split(",");
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
            String pathfile = dateTimeString + "." + extension;
            fileStorageService.storeFile(new ByteArrayInputStream(data), pathfile);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/img/")
                    .path(pathfile)
                    .toUriString();
            Service newService = new Service();
            newService.setName(service.getName());
            newService.setPrice(service.getPrice());
            newService.setImg(fileDownloadUri);
            Service savedService = serviceRepo.save(newService);
            URI productURI = URI.create("/service/" + savedService.getId());
            return ResponseEntity.created(productURI).body(savedService);
        } else {
            return ResponseEntity.ok("Dịch vụ không tồn tại");
        }


    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (serviceRepo.existsById(id) == false) {
            return ResponseEntity.ok("ID không tồn tại");
        } else {
            serviceRepo.deleteById(id);
            return ResponseEntity.ok(id);
        }
    }
}