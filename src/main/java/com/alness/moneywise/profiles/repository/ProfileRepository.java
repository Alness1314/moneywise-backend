package com.alness.moneywise.profiles.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.alness.moneywise.profiles.entity.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, UUID>,JpaSpecificationExecutor<ProfileEntity>{
    
}
