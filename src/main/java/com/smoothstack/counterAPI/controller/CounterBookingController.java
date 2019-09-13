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

import com.smoothstack.counterAPI.entity.Booking;
import com.smoothstack.counterAPI.service.CounterBookingService;

@RestController
@RequestMapping("counter")
public class CounterBookingController {

	private final String XML = "application/xml";
	private final String JSON = "application/json";
	
	@Autowired
	private CounterBookingService bookingService;
	
	@GetMapping(value ="/bookings", produces = { XML, JSON })
	public List<Booking> getAllBookings(@RequestParam(required = false, defaultValue = "100") int size) {		
		return bookingService.getAllBookings(size);
	}	
	
	@GetMapping(value = "/booking/{id}", produces = { XML, JSON })
	public ResponseEntity<Booking> getBookingById(@PathVariable Integer id) {
		return bookingService.getBookingById(id);								 
	}
	
	@GetMapping(value="/booking/traveler/{id}", produces = { JSON, XML })
	public ResponseEntity<List<Booking>> getBookingsOfTraveler(@PathVariable Integer id){
		return bookingService.getBookingsOfTraveler(id);
	}
	
	@GetMapping(value="/booking/travelagent/{id}", produces = { JSON, XML })
	public ResponseEntity<List<Booking>> getBookingsOfTravelAgent(@PathVariable Integer id){
		return bookingService.getBookingsOfTravelAgent(id);
	}
	
	@GetMapping(value="/booking/flight/{id}", produces = { JSON, XML })
	public ResponseEntity<List<Booking>> getBookingsOfFlight(@PathVariable Integer id){
		return bookingService.getBookingsOfFlight(id);
	}		
	
	@PostMapping(value ="/booking", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) {
		return bookingService.createBooking(booking);
	}	

	@PutMapping(value ="/booking", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.OK)
	public Booking updateBooking(@Valid @RequestBody Booking booking) {
		return bookingService.updateBooking(booking);
	}
	
	@DeleteMapping(value = "/booking", consumes = { JSON, XML }, produces = { JSON, XML})
	public boolean cancleBooking(@Valid @RequestBody Booking booking) {
		return bookingService.cancleBooking(booking);			
	}
	
}
