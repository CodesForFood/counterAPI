package com.smoothstack.counterAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport {

	@Id	
	@Column(name = "airport_code")
	private String code;
	
	@Column(name = "airport_name")
	private String name;
	
	@Column(name = "airport_city_name")
	private String city;
	
	@Column(name = "airport_country_name")
	private String country;
	
	public String getCode() { return code; }	
	public void setCode(String code) { this.code = code; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }

	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }	
	
}
