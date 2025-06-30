package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CustomerRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class CustomerService {
	private CustomerRepository customerRepository;
	private StoreRepository storeRepository;
	private AddressRepository addressRepository;
	
	public CustomerService(CustomerRepository customerRepository, StoreRepository storeRepository, AddressRepository addressRepository) {
		this.customerRepository = customerRepository;
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
	}
	
	public List<CustomerEntity> findAll() {
		return customerRepository.findAll();
	}
	
	public CustomerEntity findById(int customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}
	
	public void save(CustomerDto customerDto) {
		CustomerEntity saveCustomer = new CustomerEntity();
		saveCustomer.setFirstName(customerDto.getFirstName());
		saveCustomer.setLastName(customerDto.getLastName());
		saveCustomer.setEmail(customerDto.getEmail());
		saveCustomer.setActive(customerDto.getActive());
		
		StoreEntity storeEntity = storeRepository.findById(customerDto.getStoreId()).orElse(null);
		saveCustomer.setStoreEntity(storeEntity);
		
		AddressEntity addressEntity = addressRepository.findById(customerDto.getAddressId()).orElse(null);
		saveCustomer.setAddressEntity(addressEntity);
		customerRepository.save(saveCustomer);
	}
	
	public void update(CustomerDto customerDto) {
		CustomerEntity updateCustomer = customerRepository.findById(customerDto.getCustomerId()).orElse(null);
		updateCustomer.setFirstName(customerDto.getFirstName());
		updateCustomer.setLastName(customerDto.getLastName());
		updateCustomer.setEmail(customerDto.getEmail());
		updateCustomer.setActive(customerDto.getActive());
		
		StoreEntity updateStore = storeRepository.findById(customerDto.getStoreId()).orElse(null);
		updateCustomer.setStoreEntity(updateStore);
		
		AddressEntity updateAddress = addressRepository.findById(customerDto.getAddressId()).orElse(null);
		updateCustomer.setAddressEntity(updateAddress);
	}
	
	public void delete(int customerId) {
		customerRepository.deleteById(customerId);
	}
}
