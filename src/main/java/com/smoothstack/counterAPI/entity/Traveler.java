package com.smoothstack.counterAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "traveler")
public class Traveler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "traveler_id")
	private int id;
	
	@Column(name = "traveler_name")
	private String name;
	
	@Column(name = "traveler_email")
	private String email;
	
	@Column(name = "traveler_cc")
	private String creditCard;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getCreditCard() {	return creditCard; }
	public void setCreditCard(String creditCard) { this.creditCard = creditCard; }

}
