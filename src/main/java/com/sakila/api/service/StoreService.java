package com.sakila.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.entity.StoreMapping;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CustomerRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class StoreService {

    private CustomerRepository customerRepository;
	private StoreRepository storeRepository;
	private AddressRepository addressRepository;

	public StoreService(StoreRepository storeRepository, AddressRepository addressRepository, CustomerRepository customerRepository) {
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
		this.customerRepository = customerRepository;
	}
	
	public Page<StoreMapping> findAll(int currentPage) {
		final int pageSize = 10;
		int pageNumber = currentPage - 1;
		Sort sort = Sort.by("storeId").ascending();
		
		PageRequest pagerable = PageRequest.of(pageNumber, pageSize, sort);
		
		return storeRepository.findAllBy(pagerable);
	}
	
	public StoreEntity findById(int storeId) {
		return storeRepository.findById(storeId).orElse(null);
	}
	
	public void save(StoreDto storeDto) {
		StoreEntity saveStore = new StoreEntity();
		saveStore.setManagerStaffId(storeDto.getManagerStaffId());
		
		AddressEntity addressEntity = addressRepository.findById(storeDto.getAddressId()).orElse(null);
		saveStore.setAddressEntity(addressEntity);
		storeRepository.save(saveStore);
	}
	
	public void update(StoreDto storeDto) {
		StoreEntity updateStore = storeRepository.findById(storeDto.getStoreId()).orElse(null);
		updateStore.setManagerStaffId(storeDto.getManagerStaffId());
		
		AddressEntity updateAddress = addressRepository.findById(storeDto.getAddressId()).orElse(null);
		updateStore.setAddressEntity(updateAddress);
	}
	
	public boolean delete(int storeId) {
		if (customerRepository.countByStoreEntity(storeRepository.findById(storeId).orElse(null)) == 0) {
			storeRepository.deleteById(storeId);
			return true;
		}
		return false;
	}
}
