package com.sakila.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {
	Long countByAddressEntity(AddressEntity addressEntity);
}
