package com.sakila.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CityDto;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CityMapping;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional
public class CityService {
	private CityRepository cityRepository;
	private CountryRepository countryRepository;
	private AddressRepository addressRepository;

	public CityService(CityRepository cityRepository, CountryRepository countryRepository, AddressRepository addressRepository) {
		this.cityRepository = cityRepository;
		this.countryRepository = countryRepository;
		this.addressRepository = addressRepository;
	}
	
	public Page<CityMapping> findAll(int currentPage) {
		final int pageSize = 10;
		int pageNumber = currentPage - 1;
		Sort sort = Sort.by("cityId").ascending();
		
		PageRequest pagerable = PageRequest.of(pageNumber, pageSize, sort);
		return cityRepository.findAllBy(pagerable);
	}
	
	public CityEntity findById(int cityId) {
		return cityRepository.findById(cityId).orElse(null);
	}
	
	public void save(CityDto cityDto) {
		CityEntity saveCity = new CityEntity();
		saveCity.setCity(cityDto.getCity());
		
		// CountryEntity
		CountryEntity countryEntity = countryRepository.findById(cityDto.getCountryId()).orElse(null);
		saveCity.setCountryEntity(countryEntity);
		cityRepository.save(saveCity);
	}
	
	// 수정
	public void update(CityDto cityDto) {
		CityEntity updateCity = cityRepository.findById(cityDto.getCityId()).orElse(null);
		updateCity.setCity(cityDto.getCity());
		
		CountryEntity updateCountry = countryRepository.findById(cityDto.getCountryId()).orElse(null);
		updateCity.setCountryEntity(updateCountry);
	}
	
	// 삭제
	public boolean delete(int cityId) {
		if (addressRepository.countByCityEntity(cityRepository.findById(cityId).orElse(null)) == 0) {
			cityRepository.deleteById(cityId);
			return true;
		}
		return false;
	}
}
