package com.example.salonmanage.service;

import com.example.salonmanage.DTO.BookingDTO;
import com.example.salonmanage.DTO.BranchDTO;
import com.example.salonmanage.DTO.userDTO;
import com.example.salonmanage.Entities.Booking;
import com.example.salonmanage.reponsitory.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingDTO> getUserBookingHistory(Integer userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getID());
        bookingDTO.setDate(booking.getDate());
        bookingDTO.setDiscount(booking.getDiscount());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setPayment(booking.getPayment());
        bookingDTO.setTotalPrice(booking.getTotalPrice());
        bookingDTO.setNhanvien(booking.getNhanvien());

        // Convert User entity to UserDTO
        userDTO userDTO = new userDTO();
        userDTO.setId(booking.getUser().getId());
        userDTO.setPhone(booking.getUser().getPhone());

        bookingDTO.setUser(userDTO);

        // Convert Branch entity to BranchDTO
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(booking.getBranch().getId());
        branchDTO.setName(booking.getBranch().getName());
        branchDTO.setAddress(booking.getBranch().getAddress());

        bookingDTO.setBranch(branchDTO);
        return bookingDTO;
    }
}
