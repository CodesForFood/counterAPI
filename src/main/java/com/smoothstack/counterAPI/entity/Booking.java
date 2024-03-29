package com.smoothstack.counterAPI.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smoothstack.counterAPI.entity.Ticket;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@Column(name="booking_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;

	public Integer getId() { return id; }
	public void setId(Integer id) {	this.id = id; }

	public Ticket getTicket() {	return ticket; }
	public void setTicket(Ticket ticket) { this.ticket = ticket; }
	
}
