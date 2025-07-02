package com.sakila.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CityMapping;
import com.sakila.api.entity.CountryEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
	Page<CityMapping> findAllBy(Pageable pageable);
	
	Long countByCountryEntity(CountryEntity countryEntity);
}
