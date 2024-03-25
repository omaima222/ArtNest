package com.example.artnest.repositories;

import com.example.artnest.entities.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository extends JpaRepository<Commission, Long> {
}
