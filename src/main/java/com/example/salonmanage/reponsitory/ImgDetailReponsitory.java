package com.example.salonmanage.reponsitory;


import com.example.salonmanage.Entities.ImgDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgDetailReponsitory extends JpaRepository<ImgDetail, Integer> {

    List<ImgDetail> findByServiceId(Integer serviceId);
}
