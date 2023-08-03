package com.example.salonmanage.controller;

import com.example.salonmanage.DTO.BookingDTO;
import com.example.salonmanage.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/bookings/history")
public class HistoryBookingApi {
    @Autowired BookingService bookingService;

    @RolesAllowed("ROLE_CUSTOMER")
    @GetMapping("/user/{userId}")
    public List<BookingDTO> getUserBookingHistory(@PathVariable Integer userId) {
        return bookingService.getUserBookingHistory(userId);
    }
}