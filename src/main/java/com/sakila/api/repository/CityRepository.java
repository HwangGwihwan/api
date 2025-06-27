package com.sakila.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CountryEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
	Long countByCountryEntity(CountryEntity countryEntity);
}
