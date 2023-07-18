package com.example.salonmanage.reponsitory;

import com.example.salonmanage.Entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    @Query("SELECT b FROM Branch b WHERE b.status != 3")
    List<Branch> findAllWithNotRemove();
}
