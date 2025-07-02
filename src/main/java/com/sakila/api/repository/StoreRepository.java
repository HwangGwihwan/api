package com.sakila.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.entity.StoreMapping;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {
	Page<StoreMapping> findAllBy(Pageable pageable);
	
	Long countByAddressEntity(AddressEntity addressEntity);
}
