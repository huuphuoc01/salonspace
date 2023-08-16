package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventRepository extends JpaRepository<event,Integer> {
    @Query(value = "select top(1) * from event where status = 0",nativeQuery = true)
    event showevent();
    event getByDate(String date);
}
