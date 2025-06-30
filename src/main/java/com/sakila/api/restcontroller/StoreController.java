package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.service.StoreService;

@RestController
public class StoreController {
	private StoreService storeService;

	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	@GetMapping("/store")
	public ResponseEntity<List<StoreEntity>> store() {
		return new ResponseEntity<List<StoreEntity>>(storeService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/store/{storeId}")
	public ResponseEntity<StoreEntity> storeOne(@PathVariable int storeId) {
		return new ResponseEntity<StoreEntity>(storeService.findById(storeId), HttpStatus.OK);
	}
	
	@PostMapping("/store")
	public ResponseEntity<String> store(@RequestBody StoreDto storeDto) {
		storeService.save(storeDto);
		return new ResponseEntity<String>("입력성공", HttpStatus.OK);
	}
	
	@PatchMapping("/store")
	public ResponseEntity<String> updateStore(@RequestBody StoreDto storeDto) {
		storeService.update(storeDto);
		return new ResponseEntity<String>("수정성공", HttpStatus.OK);
	}
	
	@DeleteMapping("/store/{storeId}")
	public ResponseEntity<String> deleteStore(@PathVariable int storeId) {
		boolean result = storeService.delete(storeId);
		if (result) {
			return new ResponseEntity<String>("삭제성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("삭제실패", HttpStatus.OK);
	}
	
}
