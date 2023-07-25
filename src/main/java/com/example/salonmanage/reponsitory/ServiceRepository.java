package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository  extends JpaRepository<Service, Integer> {
    @Query("SELECT b FROM Service b WHERE b.status != 3")
    List<Service> findAllWithNotRemove();
}
