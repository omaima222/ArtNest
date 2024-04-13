package com.example.artnest.repositories;

import com.example.artnest.entities.Commission;
import com.example.artnest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommissionRepository extends JpaRepository<Commission, Long> {
    List<Commission> getAllByClient(User client);
}
