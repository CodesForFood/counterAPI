package com.smoothstack.counterAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="airline_counter")
public class Counter {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "counter_id")
	private int id;
	
	@Column(name="counter_name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "airport_code")
	private Airport airport;

	public int getId() { return id; }
	public void setId(int id) { this.id = id;}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public Airport getAirport() { return airport; }
	public void setAirport(Airport airport) { this.airport = airport; }
	
	
	
}
