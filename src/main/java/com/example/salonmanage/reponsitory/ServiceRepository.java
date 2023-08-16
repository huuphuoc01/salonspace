package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository  extends JpaRepository<Service, Integer> {
    @Query("SELECT b FROM Service b WHERE b.status = 1")
    List<Service> findAllWithNotRemove();

    @Query("SELECT b FROM Service b WHERE b.status != 3")
    List<Service> findAllWithNotRemoveAdmin();

    @Query("SELECT b FROM Service b WHERE b.status != 3 AND b.name LIKE %:serviceName%")
    List<Service> findAllWithNotRemoveByName(@Param("serviceName") String serviceName);

    Service findServiceById(Integer id);

    @Query("SELECT COUNT(*) FROM Service b WHERE b.status != 3")
    int countAllWithNotRemove();

    @Query(value = "SELECT s.img, s.name, s.price, COUNT(bd) AS booking_count " +
            "FROM Service s " +
            "INNER JOIN booking_detail bd ON s.id = bd.service_id " +
            "GROUP BY s.id " +
            "ORDER BY booking_count DESC " +
            "Limmit 9",
            nativeQuery = true)
    List<Object[]> findMostBookedServices();

}
