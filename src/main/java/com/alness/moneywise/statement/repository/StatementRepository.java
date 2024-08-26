package com.alness.moneywise.statement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alness.moneywise.statement.entity.StatementEntity;

public interface StatementRepository extends JpaRepository<StatementEntity, UUID>{
    
}
