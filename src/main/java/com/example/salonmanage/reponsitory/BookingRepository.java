package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking findBookingByID(Integer id);

    List<Booking> findByUserPhone(String phone);

    List<Booking> findBookingByDateAndNhanvien(String date,int nhanvien);

    @Query(value = "SELECT b FROM Booking b WHERE b.status = 0 or b.payment = 0 order by CONVERT(datetime, b.date, 103) desc", nativeQuery = true)
    List<Booking> findAllWithNotRemove();

    @Query(value ="SELECT * FROM Booking b WHERE CONVERT(datetime, b.date, 103) >= ? and CONVERT(datetime, b.date, 103) <= ? ", nativeQuery = true)
    List<Booking> getDataCalendarWithNone(String start, String end);

    @Query(value ="SELECT * FROM Booking b WHERE CONVERT(datetime, b.date, 103) >= ? and CONVERT(datetime, b.date, 103) <= ? and branch_id = ?", nativeQuery = true)
    List<Booking> getDataCalendarWithBranch(String start, String end, int branch);

    @Query("SELECT COUNT(*) FROM Booking b WHERE b.status != 0 and b.payment != 0")
    int countAllWithNotRemove();

    @Query("SELECT SUM(b.totalPrice) FROM Booking b where b.status != 0 and b.payment != 0")
    Long sumAmount();

    @Query(value ="SELECT count(*) FROM Booking b WHERE b.status != 0 and b.payment != 0 and CONVERT(datetime, b.date, 103) >= DATEADD(DAY, -30, GETDATE())", nativeQuery = true)
    int countAllWithNotRemove30DayBefore();

    @Query(value ="SELECT top(10) *  FROM Booking b  where b.status != 0 and b.payment != 0 order by CONVERT(datetime, b.date, 103) desc", nativeQuery = true)
    List<Booking> getTop10();
}
