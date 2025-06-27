package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CityRepository;

@Service
@Transactional
public class AddressService {
	private AddressRepository addressRepository;
	private CityRepository cityRepository;

	public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
		this.addressRepository = addressRepository;
		this.cityRepository = cityRepository;
	}
	
	public List<AddressEntity> findAll() {
		return addressRepository.findAll();
	}
	
	public void save(AddressDto addressDto) {
		AddressEntity saveAddress = new AddressEntity();
		saveAddress.setAddress(addressDto.getAddress());
		saveAddress.setAddress2(addressDto.getAddress2());
		saveAddress.setDistrict(addressDto.getDistrict());
		saveAddress.setPostalCode(addressDto.getPostalCode());
		saveAddress.setPhone(addressDto.getPhone());
		
		CityEntity cityEntity = cityRepository.findById(addressDto.getCityId()).orElse(null);
		saveAddress.setCityEntity(cityEntity);
		addressRepository.save(saveAddress);
		
	}
	
	// 수정
	public void update(AddressDto addressDto) {
		AddressEntity updateAddress = addressRepository.findById(addressDto.getAddressId()).orElse(null);
		updateAddress.setAddress(addressDto.getAddress());
		updateAddress.setAddress2(addressDto.getAddress2());
		updateAddress.setDistrict(addressDto.getDistrict());
		updateAddress.setPostalCode(addressDto.getPostalCode());
		updateAddress.setPhone(addressDto.getPhone());
	}
	
}
