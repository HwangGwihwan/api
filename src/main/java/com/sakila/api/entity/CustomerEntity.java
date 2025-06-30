package com.sakila.api.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@DynamicInsert
@Getter
@Setter
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "create_date", nullable = true)
	private Timestamp createDate;
	
	@Column(name = "last_update", nullable = true)
	private Timestamp lastUpdate;
	
	@ManyToOne
	@JoinColumn(name = "store_id")
	private StoreEntity storeEntity;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private AddressEntity addressEntity;
}
