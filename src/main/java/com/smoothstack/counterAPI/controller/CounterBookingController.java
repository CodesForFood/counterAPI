package com.smoothstack.counterAPI.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.counterAPI.entity.Booking;
import com.smoothstack.counterAPI.service.CounterBookingService;

@RestController
@RequestMapping("/counter")
public class CounterBookingController {

	private final String JSON = "application/json";
	private final String XML = "application/xml";
	
	@Autowired
	private CounterBookingService bookingService;
	
	
	@GetMapping(value = "/bookings", produces = { XML, JSON })
	public List<Booking> getAllBookings(@RequestParam(required = false, defaultValue = "100") int size,
			@RequestParam(required = false, defaultValue = "1") int page) {		
		return bookingService.getAllBookings(size, page);
	}	
	
	@GetMapping(value = "/bookings/{id}", produces = { XML, JSON })
	public ResponseEntity<List<Booking>> getBookingsById(@PathVariable Integer id) {
		return bookingService.getBookingsById(id);								 
	}	
	
	@GetMapping(value = "/bookings/flight/{id}", produces = { JSON, XML})
	public ResponseEntity<List<Booking>> getBookingsOfFlight(@RequestParam(required = false, defaultValue = "1") int page, @PathVariable Integer id){
		return bookingService.getBookingsOfFlight(page, id);
	}
	
	@GetMapping(value = "/bookings/traveler/{id}", produces = { JSON, XML})
	public ResponseEntity<List<Booking>> getBookingsOfTraveler(@PathVariable Integer id){
		return bookingService.getBookingsOfTraveler(id);
	}
	
	@PostMapping(value = "/booking", consumes = { JSON, XML }, produces = { JSON, XML })
	public Booking createBooking(@Valid @RequestBody Booking booking){
		return bookingService.createBooking(booking);
	}
	
	@PutMapping(value = "/booking", consumes = { JSON, XML }, produces = { JSON, XML })
	public Booking updateBooking(@Valid @RequestBody Booking booking) {
		return bookingService.updateBooking(booking);
	}
	
	
}
