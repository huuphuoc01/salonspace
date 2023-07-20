package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.ServiceDTO;
import com.example.salonmanage.Entities.Branch;
import com.example.salonmanage.Entities.Service;
import com.example.salonmanage.reponsitory.BranchRepository;
import com.example.salonmanage.reponsitory.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired private BranchRepository branchReponsitory;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ServiceDTO service) {
        try {
            if ( branchReponsitory.existsById(service.getBranch())==true){
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
                String pathfile = "D://img/"+dateTimeString+"."+extension;
                File file = new File(pathfile);
                OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                outputStream.write(data);
                Branch branchEnity = branchReponsitory.getById(service.getBranch());
                Service newService = new Service();
                newService.setName(service.getName());
                newService.setPrice(service.getPrice());
                newService.setImg(pathfile);
                Service savedService = serviceRepo.save(newService);
                URI productURI = URI.create("/service/" + savedService.getId());
                return ResponseEntity.created(productURI).body(savedService);
            } else {
                return ResponseEntity.ok("chi nhánh không tồn tại");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("một lỗi vô tình xảy ra trên máy chủ");
        }
    }

    @GetMapping
    public List<Service> list() {
        return serviceRepo.findAll();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update( @PathVariable Integer id,@RequestBody  ServiceDTO service) {
        try {
            if ( branchReponsitory.existsById(service.getBranch())==true){
                if ( serviceRepo.existsById(service.getId())==true) {
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
                    String pathfile = "D://img/" + dateTimeString + "." + extension;
                    File file = new File(pathfile);
                    OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                    outputStream.write(data);
                    Branch branchEnity = branchReponsitory.getById(service.getBranch());
                    Service newService = new Service();
                    newService.setName(service.getName());
                    newService.setPrice(service.getPrice());
                    newService.setImg(pathfile);
                    Service savedService = serviceRepo.save(newService);
                    URI productURI = URI.create("/service/" + savedService.getId());
                    return ResponseEntity.created(productURI).body(savedService);
                }else{
                    return ResponseEntity.ok("Dịch vụ không tồn tại");
                }
            } else {
                return ResponseEntity.ok("chi nhánh không tồn tại");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("một lỗi vô tình xảy ra trên máy chủ");
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if ( serviceRepo.existsById(id)==false){
            return ResponseEntity.ok("ID không tồn tại");
        }else {
            serviceRepo.deleteById(id);
            return ResponseEntity.ok(id);
        }
    }
}///