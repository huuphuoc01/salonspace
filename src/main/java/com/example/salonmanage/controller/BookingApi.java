package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.BookDTO;
import com.example.salonmanage.DTO.CartDTO;
import com.example.salonmanage.DTO.TimeDTO;
import com.example.salonmanage.Entities.*;
import com.example.salonmanage.reponsitory.*;
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
    private TimesRepository timesRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private BookingDetailRepository bookingDetailRepository;
    @Autowired
    private BranchRepository branchRepository;


    @PostMapping("/addToCart")
    public void addToCart(@RequestBody CartDTO cartDTO) {
        User user = userService.findByPhone(cartDTO.getPhone());
        System.out.println("--------------------------");
        System.out.println(user.getRoles());
        System.out.println("-------------------------------");
        BookingDetail bookingDetail = new BookingDetail();
//        bookingDetail.setBooking();
        Service service = serviceRepository.findServiceById(cartDTO.getServiceID());
        Booking booking = bookingRepository.findBookingByID(2);
        System.out.println(booking.getDiscount());
        bookingDetail.setBooking(booking);
        bookingDetail.setService(service);
        bookingDetail.setUser(user);
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
    @GetMapping("/listStaff")
    public ResponseEntity<?> listStaff(){
         List<User> list= userService.getAll();
         List<User> list1 = new ArrayList<>();
        Role role = roleRepository.findByName("ROLE_CUSTOMER");
        System.out.println(role);
        System.out.println("----------------------------------");
        for (User u:list
             ) {
//            System.out.println(u.getRoles());
            Role role1 = roleRepository.findByName(u.getRoles().toString());
            System.out.println(u.getRoles().toString());
            if(u.getRoles().toString().equals("[ROLE_STAFF]")){
                list1.add(u);
                System.out.println(u);
            }
        }
        return ResponseEntity.ok().body(list1);
    }
    @PostMapping("/listTime")
    public ResponseEntity<?> listTime(@RequestBody TimeDTO timeDTO) {
        List<Booking> list = bookingRepository.findBookingByDateAndNhanvien(timeDTO.getDate(), timeDTO.getStaffId());
        for (Booking b : list
        ) {
            System.out.println(b.getTimes());
        }
        List<Times> times = timesRepository.findAll();
        System.out.println(times);
        for (Booking bo : list
        ) {
            for (Times t : times
            ) {
                if (t.getID() == bo.getTimes().getID()) {
                    System.out.println("thế á");
                    System.out.println(t);
                    times.remove(t);
                    break;

                }
            }
        }

        return ResponseEntity.ok().body(times);
    }
        @PostMapping("/book")
        public ResponseEntity<?> book(@RequestBody BookDTO bookDTO){
            System.out.println(bookDTO);
            User user = userService.findByPhone(bookDTO.getUser());
            Booking booking = new Booking();
            booking.setDate(bookDTO.getDate());
            booking.setNhanvien(bookDTO.getNhanvien());
            booking.setUser(user);
            Times times = timesRepository.getById(bookDTO.getTime());
            booking.setTimes(times);
            Branch branch = branchRepository.getById(bookDTO.getBranch());
            booking.setBranch(branch);
            booking.setTotalPrice(bookDTO.getTotalPrice());
            booking.setDiscount(0);
            booking.setStatus(0);
            booking.setPayment(0);
            System.out.println(booking);
            bookingRepository.save(booking);
            return ResponseEntity.ok("ss");
        }
    }

