package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.CartDTO;
import com.example.salonmanage.Entities.Booking;
import com.example.salonmanage.Entities.BookingDetail;
import com.example.salonmanage.Entities.Service;
import com.example.salonmanage.Entities.User;
import com.example.salonmanage.reponsitory.BookingDetailRepository;
import com.example.salonmanage.reponsitory.BookingRepository;
import com.example.salonmanage.reponsitory.ServiceRepository;
import com.example.salonmanage.service.BookingDetailService;
import com.example.salonmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class BookingApi {
    @Autowired
    private BookingDetailService bookingDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private BookingDetailRepository bookingDetailRepository;

    @PostMapping("/addToCart")
    public void addToCart(@RequestBody CartDTO cartDTO) {
        User user = userService.findByPhone(cartDTO.getPhone());
        System.out.println(user);
        BookingDetail bookingDetail = new BookingDetail();
//        bookingDetail.setBooking();
        Service service = serviceRepository.findServiceById(1);
        Booking booking = bookingRepository.findBookingByID(1);
        System.out.println(booking.getDiscount());
        bookingDetail.setBooking(booking);
        bookingDetail.setService(service);
        bookingDetail.setCus_id(user.getId());
        bookingDetail.setStatus(0);
        System.out.println(user.getId());

        BookingDetail B = bookingDetailRepository.save(bookingDetail);
        System.out.println(bookingDetail);

    }

    @PostMapping("/listCart")
    public ResponseEntity<?> listCart(@RequestBody CartDTO cartDTO) {
        User user = userService.findByPhone(cartDTO.getPhone());
        List<BookingDetail> list = bookingDetailRepository.findByBookingId(user.getId());
        List<Service> list1 = new ArrayList<>();
        for (BookingDetail a : list
        ) {
            System.out.println(a);
            list1.add(a.getService());
        }
        for (Service b : list1
        ) {
            System.out.println(b);
        }
        return ResponseEntity.ok().body(list1);
    }

    @GetMapping
    public List<Booking> get() {
        return bookingRepository.findAllWithNotRemove();
    }
}
