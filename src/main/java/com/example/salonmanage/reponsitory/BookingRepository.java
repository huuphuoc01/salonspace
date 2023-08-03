package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking findBookingByID(Integer id);

}
