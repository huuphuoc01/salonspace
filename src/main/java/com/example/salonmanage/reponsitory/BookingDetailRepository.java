package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail,Long> {
    public BookingDetail save(BookingDetail bookingDetail);
    @Query(value = "select * from booking_detail where user_id = ? and status = 0;", nativeQuery = true)
    List<BookingDetail> findByBookingId(Integer id);
    @Query(value = "select * from booking_detail where booking_id = ?", nativeQuery = true)
    List<BookingDetail> findByBookingId1(Integer id);
}
