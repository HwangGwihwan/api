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

import com.sakila.api.dto.CountryDto;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.service.CountryService;

@RestController
public class CountryController {
	private CountryService countryService;

	public CountryController(CountryService countryService) {
		this.countryService = countryService;
	}

	@GetMapping("/country")
	public ResponseEntity<List<CountryEntity>> country() {
		return new ResponseEntity<List<CountryEntity>>(countryService.findAll(), HttpStatus.OK);
	}
	
	// 입력
	@PostMapping("/country")
	public ResponseEntity<String> country(@RequestBody CountryDto countryDto) {
		countryService.save(countryDto);
		return new ResponseEntity<String>("입력성공", HttpStatus.OK);
	}
	
	// 수정
	@PatchMapping("/country")
	public ResponseEntity<String> updateCountry(@RequestBody CountryDto countryDto) {
		countryService.update(countryDto);
		return new ResponseEntity<String>("수정성공", HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/country/{countryId}")
	public ResponseEntity<String> deleteCountry(@PathVariable int countryId) {
		boolean result = countryService.delete(countryId); // 자식테이블에 외래키로 참조하는 행이 있다면?
		if (result) {
			return new ResponseEntity<String>("삭제성공", HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("삭제실패", HttpStatus.OK);
	}
}


