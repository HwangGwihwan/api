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

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.AddressMapping;
import com.sakila.api.service.AddressService;

@RestController
@CrossOrigin
public class AddressController {
	private AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@GetMapping("/addressList/{currentPage}")
	public ResponseEntity<Page<AddressMapping>> address(@PathVariable int currentPage) {
		return new ResponseEntity<Page<AddressMapping>>(addressService.findAll(currentPage), HttpStatus.OK);
	}
	
	@GetMapping("/address/{addressId}")
	public ResponseEntity<AddressEntity> addressOne(@PathVariable int addressId) {
		return new ResponseEntity<AddressEntity>(addressService.findById(addressId), HttpStatus.OK);
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
		return new ResponseEntity<String>("수정성공", HttpStatus.OK);
	}
	
	@DeleteMapping("/address/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable int addressId) {
		boolean result = addressService.delete(addressId); // 자식테이블에 외래키로 참조하는 행이 있다면?
		if (result) {
			return new ResponseEntity<String>("삭제성공", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
