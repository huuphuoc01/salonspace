package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {

}
