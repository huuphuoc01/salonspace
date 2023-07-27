package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.ServiceDTO;
import com.example.salonmanage.Entities.Branch;
import com.example.salonmanage.Entities.ImgDetail;
import com.example.salonmanage.Entities.Service;
import com.example.salonmanage.reponsitory.ImgDetailReponsitory;
import com.example.salonmanage.reponsitory.ServiceRepository;
import com.example.salonmanage.service.FileStorageService;
import com.example.salonmanage.service.ImgDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "*")
public class ServiceApi {
    @Autowired
    private ServiceRepository serviceRepo;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired ImgDetailReponsitory imgDetailReponsitory;

    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
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
        newService.setDescription(service.getDescription());
        newService.setStatus(1);
        Service savedService = serviceRepo.save(newService);
        int count =  service.getImgList().length;
        String[] stringsImg;
        String extensionImg;
        String pathfileImg;
        String fileDownloadUriImg;
        byte[] dataImg;
        String idService = String.valueOf(savedService.getId());
        for (int i=0; i<count; i++ ){
            stringsImg = service.getImgList()[i].split(",");
            switch (stringsImg[0]) {//check image's extension
                case "data:image/jpeg;base64":
                    extensionImg = "jpeg";
                    break;
                case "data:image/png;base64":
                    extensionImg = "png";
                    break;
                default://should write cases for more images types
                    extensionImg = "jpg";
                    break;
            }
            pathfileImg = "service-id"+idService+"-"+String.valueOf(i)+"."+extensionImg;
            dataImg = DatatypeConverter.parseBase64Binary(stringsImg[1]);
            fileStorageService.storeFile(new ByteArrayInputStream(dataImg), pathfileImg);
            fileDownloadUriImg = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/img/")
                    .path(pathfileImg)
                    .toUriString();
            ImgDetail imgDetail = new ImgDetail(fileDownloadUriImg,savedService);
            imgDetailReponsitory.save(imgDetail);
        }
        URI productURI = URI.create("/service/" + savedService.getId());
        return ResponseEntity.created(productURI).body(savedService);
    }

    @GetMapping
    public List<Service> list() {
        return serviceRepo.findAllWithNotRemove();
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<?>  listId(@PathVariable Integer id) {
//        return branchRepo.findById(id).orElse(null);
        if ( !serviceRepo.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok().body(serviceRepo.findById(id).get());
        }
    }
    @PutMapping("{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ServiceDTO service) {
        if (serviceRepo.existsById(service.getId()) == true && id==service.getId()) {
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

            Service newService = serviceRepo.findById(id).get();
            List<ImgDetail> imgDetails = new ArrayList<>(newService.getImgDetails());
            String[] pathSegments = newService.getImg().split("/");
            fileStorageService.removeFile(pathSegments[pathSegments.length - 1]);
            newService.setName(service.getName());
            newService.setPrice(service.getPrice());
            newService.setImg(fileDownloadUri);
            newService.setDescription(service.getDescription());
            newService.setStatus(service.getStatus());
            Service savedService = serviceRepo.save(newService);
            savedService.getImgDetails();
            int count =  service.getImgList().length;
            String[] stringsImg;
            String extensionImg;
            String pathfileImg;
            String fileDownloadUriImg;
            byte[] dataImg;
            String idService = String.valueOf(savedService.getId());
            for (int i=0; i<count; i++ ){
                stringsImg = service.getImgList()[i].split(",");
                switch (stringsImg[0]) {//check image's extension
                    case "data:image/jpeg;base64":
                        extensionImg = "jpeg";
                        break;
                    case "data:image/png;base64":
                        extensionImg = "png";
                        break;
                    default://should write cases for more images types
                        extensionImg = "jpg";
                        break;
                }
                pathfileImg = "service-id"+idService+"-"+String.valueOf(i)+"."+extensionImg;
                dataImg = DatatypeConverter.parseBase64Binary(stringsImg[1]);
                fileStorageService.storeFile(new ByteArrayInputStream(dataImg), pathfileImg);
                fileDownloadUriImg = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/img/")
                        .path(pathfileImg)
                        .toUriString();
                ImgDetail imgDetail = new ImgDetail(fileDownloadUriImg,savedService);
                imgDetailReponsitory.save(imgDetail);
            }
            URI productURI = URI.create("/service/" + savedService.getId());
            return ResponseEntity.created(productURI).body(savedService);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (serviceRepo.existsById(id) == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            Service update = serviceRepo.findById(id).get();
            update.setStatus(3);
            serviceRepo.save(update);
            return ResponseEntity.ok(id);
        }
    }
}