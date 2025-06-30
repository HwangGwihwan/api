package com.sakila.api.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CustomerDto {
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private int active;
	private Timestamp createDate;
	private Timestamp lastUpdate;
	private int storeId;
	private int addressId;
}
