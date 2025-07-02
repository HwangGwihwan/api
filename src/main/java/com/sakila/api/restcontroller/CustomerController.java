package com.sakila.api.restcontroller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.CustomerMapping;
import com.sakila.api.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/customerList/{currentPage}")
	public ResponseEntity<Page<CustomerMapping>> customer(@PathVariable int currentPage) {
		return new ResponseEntity<Page<CustomerMapping>>(customerService.findAll(currentPage), HttpStatus.OK);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerEntity> customerOne(@PathVariable int customerId) {
		return new ResponseEntity<CustomerEntity>(customerService.findById(customerId), HttpStatus.OK);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<String> customer(@RequestBody CustomerDto customerDto) {
		customerService.save(customerDto);
		return new ResponseEntity<String>("입력성공", HttpStatus.OK);
	}
	
	@PatchMapping("/customer")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto) {
		customerService.update(customerDto);
		return new ResponseEntity<String>("수정성공", HttpStatus.OK);
	}
	
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
		customerService.delete(customerId);
		return new ResponseEntity<String>("삭제성공", HttpStatus.OK);
	}
}
