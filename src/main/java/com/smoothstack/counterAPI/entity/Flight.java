package com.smoothstack.counterAPI.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="flight")
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flight_id")
	private int id;
	
	@Column(name = "flight_dep_time")
	private LocalDateTime departTime;
	
	@Column(name = "flight_des_time")
	private LocalDateTime destinationTime;
	
	@ManyToOne
	@JoinColumn(name = "airport_dep_code")
	private Airport departAirport;
	
	@ManyToOne
	@JoinColumn(name = "airport_des_code")
	private Airport destinationAirport;
	
	@Column(name = "ticket_price")
	private double ticketPrice;
	
	@Column(name = "number_of_tickets")
	private int numOfTickets;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDepartTime() {
		return departTime;
	}

	public void setDepartTime(LocalDateTime departTime) {
		this.departTime = departTime;
	}

	public LocalDateTime getDestinationTime() {
		return destinationTime;
	}

	public void setDestinationTime(LocalDateTime destinationTime) {
		this.destinationTime = destinationTime;
	}

	public Airport getDepartAirport() {
		return departAirport;
	}

	public void setDepartAirport(Airport depertAirport) {
		this.departAirport = depertAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getNumOfTickets() {
		return numOfTickets;
	}

	public void setNumOfTickets(int numOfTickets) {
		this.numOfTickets = numOfTickets;
	}
	
	
	
}
