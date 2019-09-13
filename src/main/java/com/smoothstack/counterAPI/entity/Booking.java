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
@Table(name="booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "traveler_id")
	private Traveler traveler;
	
	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;
	
	@ManyToOne
	@JoinColumn(name = "travel_agent_id")
	private TravelAgent travelAgent;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public Traveler getTraveler() {	return traveler; }
	public void setTraveler(Traveler traveler) { this.traveler = traveler; }

	public Flight getFlight() {	return flight; }
	public void setFlight(Flight flight) { this.flight = flight; }

	public TravelAgent getTravleAgent() { return travelAgent; }
	public void setTravleAgent(TravelAgent travleAgent) { this.travelAgent = travleAgent; }
	
}
