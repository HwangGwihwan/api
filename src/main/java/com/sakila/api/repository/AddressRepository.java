package com.sakila.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CountryEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer>{
	Long countByCityEntity(CityEntity cityEntity);
}
