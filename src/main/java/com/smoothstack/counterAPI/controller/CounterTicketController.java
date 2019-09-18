package com.smoothstack.counterAPI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.counterAPI.entity.Ticket;
import com.smoothstack.counterAPI.service.CounterTicketService;

@RestController
@RequestMapping("/counter")
public class CounterTicketController {

	private final String XML = "application/xml";
	private final String JSON = "application/json";
	
	@Autowired
	private CounterTicketService ticketService;
	
	@GetMapping(value ="/tickets", produces = { XML, JSON })
	public List<Ticket> getAllTickets(@RequestParam(required = false, defaultValue = "100") int size) {		
		return ticketService.getAllTickets(size);
	}	
	
	@GetMapping(value = "/ticket/{id}", produces = { XML, JSON })
	public ResponseEntity<Ticket> getTicketById(@PathVariable Integer id) {
		return ticketService.getTicketById(id);								 
	}
	
	@GetMapping(value="/tickets/traveler/{id}", produces = { JSON, XML })
	public ResponseEntity<List<Ticket>> getTicketsOfTraveler(@PathVariable Integer id){
		return ticketService.getTicketsOfTraveler(id);
	}
	
	@GetMapping(value="/tickets/travelagent/{id}", produces = { JSON, XML })
	public ResponseEntity<List<Ticket>> getTicketsOfTravelAgent(@PathVariable Integer id){
		return ticketService.getTicketsOfTravelAgent(id);
	}
	
	@GetMapping(value="/tickets/flight/{id}", produces = { JSON, XML })
	public ResponseEntity<List<Ticket>> getTicketsOfFlight(@PathVariable Integer id){
		return ticketService.getTicketsOfFlight(id);
	}		
	
	@PostMapping(value ="/ticket", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket) {
		return ticketService.createTicket(ticket);
	}	

	@PutMapping(value ="/ticket", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.OK)
	public Ticket updateTicket(@Valid @RequestBody Ticket ticket) {
		return ticketService.updateTicket(ticket);
	}
	
	@DeleteMapping(value = "/ticket", consumes = { JSON, XML }, produces = { JSON, XML})
	public ResponseEntity<Ticket> cancleTicket(@Valid @RequestBody Ticket ticket) {
		return ticketService.cancleTicket(ticket);			
	}
	
}
