package com.sakila.api.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CityDto {
	private int cityId;
	private String city;
	private Timestamp lastUpdate;
	private int countryId;
}
