package com.sakila.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {
}
