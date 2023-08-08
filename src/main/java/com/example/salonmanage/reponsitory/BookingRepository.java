package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking findBookingByID(Integer id);

    List<Booking> findByUserId(Integer userId);
    List<Booking> findBookingByDateAndNhanvien(String date,int nhanvien);

    @Query("SELECT b FROM Booking b WHERE b.status = 0 or b.payment = 0 order by b.date desc")
    List<Booking> findAllWithNotRemove();
}
