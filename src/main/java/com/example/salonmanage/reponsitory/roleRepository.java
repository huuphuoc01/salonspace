package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface RoleRepository  extends JpaRepository<Role, Integer> {
Role findByName(String name);
=======
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //
    Role findByName(String name);
>>>>>>> 0d7fe4ad9cdb0130600beaa87933049d609fb6a6
}
