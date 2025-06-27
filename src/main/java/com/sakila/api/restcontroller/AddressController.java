package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.service.AddressService;

@RestController
public class AddressController {
	private AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@GetMapping("/address")
	public ResponseEntity<List<AddressEntity>> address() {
		return new ResponseEntity<List<AddressEntity>>(addressService.findAll(), HttpStatus.OK);
	}
	
	// 입력
	@PostMapping("/address")
	public ResponseEntity<String> address(@RequestBody AddressDto addressDto) {
		addressService.save(addressDto);
		return new ResponseEntity<String>("입력성공", HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/address")
	public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto) {
		addressService.update(addressDto);
		return new ResponseEntity<String>("입력성공", HttpStatus.OK);
	}
}
