package com.smoothstack.counterAPI.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.counterAPI.dao.CounterFlightDAO;
import com.smoothstack.counterAPI.dao.CounterTicketDAO;
import com.smoothstack.counterAPI.dao.CounterTravelerDAO;
import com.smoothstack.counterAPI.entity.Flight;
import com.smoothstack.counterAPI.entity.Ticket;
import com.smoothstack.counterAPI.entity.Traveler;

@Service
public class CounterTicketService {
	
	@Autowired
	private CounterTicketDAO ticketDAO;
	
	@Autowired
	private CounterTravelerDAO travelerDAO;
	
	@Autowired
	private CounterFlightDAO flightDAO;
	
	@Transactional
	public List<Ticket> getAllTickets(int size) {
		Pageable limit = PageRequest.of(0,size);
		return ticketDAO.findAll(limit).getContent();		
	}

	@Transactional
	public ResponseEntity<Ticket> getTicketById(Integer id) {
		Optional<Ticket> ticket = ticketDAO.findById(id);		
				
		return !ticket.isPresent() ? new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND) 
			: new ResponseEntity<Ticket>(ticket.get(), HttpStatus.OK);						 
	}
	
	@Transactional
	public ResponseEntity<List<Ticket>> getTicketsOfTraveler(Integer id){
		Optional<List<Ticket>> ticket = ticketDAO.getTicketsOfTravler(id);
		
		return !ticket.isPresent() ? new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND) 
				: new ResponseEntity<List<Ticket>>(ticket.get(), HttpStatus.OK);			
	}
	
	@Transactional
	public ResponseEntity<List<Ticket>> getTicketsOfFlight(Integer id){
		Optional<List<Ticket>> ticket = ticketDAO.getTicketsOfFlight(id);
		
		return !ticket.isPresent() ? new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND) 
				: new ResponseEntity<List<Ticket>>(ticket.get(), HttpStatus.OK);			
	}
	
	@Transactional
	public ResponseEntity<List<Ticket>> getTicketsOfTravelAgent(Integer id){
		Optional<List<Ticket>> ticket = ticketDAO.getTicketsOfTravelAgent(id);
		
		return !ticket.isPresent() ? new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND) 
				: new ResponseEntity<List<Ticket>>(ticket.get(), HttpStatus.OK);			
	}	
	
	@Transactional
	public ResponseEntity<Ticket> createTicket(Ticket ticket) {
		final Optional<Flight> flight = flightDAO.findById(ticket.getFlight().getId()); 
		final Optional<Traveler> traveler = travelerDAO.findById(ticket.getTraveler().getId());
		
		double price;
		int numOfTickets;
		double funds;
		
		if(flight.isPresent()) {
			price = flight.get().getTicketPrice();
			numOfTickets = flight.get().getNumOfTickets();
		}
		else {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
		
		if(traveler.isPresent()) {
			funds = traveler.get().getFunds();
		}
		else {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}								
		
		if(funds < price) {
			return new ResponseEntity<Ticket>(HttpStatus.PAYMENT_REQUIRED);
		}		
		else if(numOfTickets < 0) {
			return new ResponseEntity<Ticket>(HttpStatus.BAD_REQUEST);
		}
		else{
			Ticket savedTicket = ticketDAO.save(ticket);
			ticketDAO.decrementFlightTickets(savedTicket.getFlight().getId());
			ticketDAO.chargeTraveler(ticket.getTraveler().getId(), ticket.getFlight().getId());
			return new ResponseEntity<Ticket>(savedTicket, HttpStatus.CREATED);
			
		}							
	}	
	
	@Transactional
	public ResponseEntity<Ticket> cancleTicket(Ticket ticket) {		
		final Optional<Flight> flight = flightDAO.findById(ticket.getFlight().getId()); 
		final Optional<Traveler> traveler = travelerDAO.findById(ticket.getTraveler().getId());
		
		double price;
		int numOfTickets;
		double funds;
		
		if(flight.isPresent()) {
			price = flight.get().getTicketPrice();
			numOfTickets = flight.get().getNumOfTickets();
		}
		else {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
		
		if(traveler.isPresent()) {
			funds = traveler.get().getFunds();
		}
		else {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}								
		
		if(funds < price) {
			return new ResponseEntity<Ticket>(HttpStatus.PAYMENT_REQUIRED);
		}		
		else if(numOfTickets < 0) {
			return new ResponseEntity<Ticket>(HttpStatus.BAD_REQUEST);
		}
		else {
			ticketDAO.deleteById(ticket.getId());	
			ticketDAO.incrementFlightTickets(ticket.getFlight().getId());
			ticketDAO.refundTravelerFunds(ticket.getTraveler().getId(), ticket.getFlight().getId());
			return new ResponseEntity<Ticket>(HttpStatus.OK);
		}					
	}

	@Transactional
	public Ticket updateTicket(Ticket ticket) {
		return ticketDAO.save(ticket);
	}	
}
