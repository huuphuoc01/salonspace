package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.BookingDTO;
import com.example.salonmanage.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/bookings/history")
@CrossOrigin(origins = "*")
public class HistoryBookingApi {
    @Autowired BookingService bookingService;

    @RolesAllowed("ROLE_CUSTOMER")
    @GetMapping("/user/{userId}")
    public List<BookingDTO> getUserBookingHistory(@PathVariable Integer userId) {
        return bookingService.getUserBookingHistory(userId);
    }
}