package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface roleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
