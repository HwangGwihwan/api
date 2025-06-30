package com.sakila.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.StoreEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
	Long countByStoreEntity(StoreEntity storeEntity);
	Long countByAddressEntity(AddressEntity addressEntity);
}
