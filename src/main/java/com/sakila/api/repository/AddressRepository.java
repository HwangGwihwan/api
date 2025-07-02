package com.sakila.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.AddressMapping;
import com.sakila.api.entity.CityEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{
	Page<AddressMapping> findAllBy(Pageable pageable);
	
	Long countByCityEntity(CityEntity cityEntity);
}
