package com.example.salonmanage.service;

import com.example.salonmanage.DTO.*;
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

    public List<BookingHistoryDTO> getUserBookingHistoryByPhone(String phone) {
        List<Booking> bookings = bookingRepository.findByUserPhone(phone);
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<BookingHistoryDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BookingHistoryDTO convertToDTO(Booking booking) {
        BookingHistoryDTO bookingHistoryDTO = new BookingHistoryDTO();
        bookingHistoryDTO.setId(booking.getID());
        bookingHistoryDTO.setDate(booking.getDate());
        bookingHistoryDTO.setDiscount(booking.getDiscount());
        bookingHistoryDTO.setStatus(booking.getStatus());
        bookingHistoryDTO.setPayment(booking.getPayment());
        bookingHistoryDTO.setTotalPrice(booking.getTotalPrice());
        bookingHistoryDTO.setNhanvienName(booking.getUser().getName());

        // Convert User entity to UserDTO
        userDTO userDTO = new userDTO();
        userDTO.setId(booking.getUser().getId());
        userDTO.setPhone(booking.getUser().getPhone());

        bookingHistoryDTO.setUser(userDTO);

        // Convert Branch entity to BranchDTO
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(booking.getBranch().getId());
        branchDTO.setName(booking.getBranch().getName());
        branchDTO.setAddress(booking.getBranch().getAddress());

        bookingHistoryDTO.setBranch(branchDTO);

        SetTime setTime = new SetTime();
        setTime.setTimes(booking.getTimes().getTime());
        bookingHistoryDTO.setTime(setTime);

        return bookingHistoryDTO;
    }
}
