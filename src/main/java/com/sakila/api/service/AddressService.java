package com.sakila.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.AddressMapping;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CustomerRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class AddressService {
	private AddressRepository addressRepository;
	private CityRepository cityRepository;
	private CustomerRepository customerRepository;
	private StoreRepository storeRepository;

	public AddressService(AddressRepository addressRepository, CityRepository cityRepository, CustomerRepository customerRepository, StoreRepository storeRepository) {
		this.addressRepository = addressRepository;
		this.cityRepository = cityRepository;
		this.customerRepository = customerRepository;
		this.storeRepository = storeRepository;
	}
	
	public Page<AddressMapping> findAll(int currentPage) {
		final int pageSize = 10;
		int pageNumber = currentPage - 1;
		Sort sort = Sort.by("addressId").descending();
		
		PageRequest pagerable = PageRequest.of(pageNumber, pageSize, sort);
		return addressRepository.findAllBy(pagerable);
	}
	
	public AddressEntity findById(int addressId) {
		return addressRepository.findById(addressId).orElse(null);
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
		
		CityEntity updateCity = cityRepository.findById(addressDto.getCityId()).orElse(null);
		updateAddress.setCityEntity(updateCity);
	}
	
	public boolean delete(int addressId) {
		if (customerRepository.countByAddressEntity(addressRepository.findById(addressId).orElse(null)) == 0 && storeRepository.countByAddressEntity(addressRepository.findById(addressId).orElse(null)) == 0) {
			addressRepository.deleteById(addressId);
			return true;
		}
		return false;
	}
}
