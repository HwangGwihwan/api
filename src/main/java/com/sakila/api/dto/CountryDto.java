package com.sakila.api.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CountryDto {
	private int countryId;
	private String country;
	private Timestamp lastUpdate;
}
