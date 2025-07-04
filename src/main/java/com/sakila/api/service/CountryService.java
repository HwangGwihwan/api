package com.sakila.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CountryDto;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.entity.CountryMapping;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional
public class CountryService {
	private CountryRepository countryRepository;
	private CityRepository cityRepository;
	
	// 필드 주입 대신 생성자 주입을 사용
	public CountryService(CountryRepository countryRepository, CityRepository cityRepository) {
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
	}
	
	public Page<CountryMapping> findAll(int currentPage) {
		final int pageSize = 10;
		int pageNumber = currentPage - 1;
		Sort sort = Sort.by("countryId").descending();
		
		PageRequest pagerable = PageRequest.of(pageNumber, pageSize, sort);
		return countryRepository.findAllBy(pagerable);
	}
	
	public CountryEntity findById(int countryId) {
		return countryRepository.findById(countryId).orElse(null);
	}
	
	// CountryEntity 입력
	public void save(CountryDto countryDto) {
		CountryEntity saveCountry = new CountryEntity();
		saveCountry.setCountry(countryDto.getCountry());
		countryRepository.save(saveCountry);
	}
	
	// country 수정
	public void update(CountryDto countryDto) {
		CountryEntity updateCountry = countryRepository.findById(countryDto.getCountryId()).orElse(null);
		updateCountry.setCountry(countryDto.getCountry());
	}
	
	// country 삭제
	public boolean delete(int countryId) {
		// 자식테이블에 참조하는 행이 없다면(select count(*) from city where country_id = ?)
		if (cityRepository.countByCountryEntity(countryRepository.findById(countryId).orElse(null)) == 0) {
			countryRepository.deleteById(countryId);
			return true;
		}
		return false;
	}
}
