package com.alness.moneywise.users.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alness.moneywise.users.entity.DetailEntity;

public interface DetailRepository extends JpaRepository<DetailEntity, UUID>{
    
}
