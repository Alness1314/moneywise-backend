package com.alness.moneywise.purchase.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alness.moneywise.purchase.entity.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, UUID>, JpaSpecificationExecutor<PurchaseEntity>{
    
}
