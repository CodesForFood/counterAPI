package com.smoothstack.counterAPI.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.counterAPI.entity.Flight;
import com.smoothstack.counterAPI.service.CounterFlightService;

@RestController
@RequestMapping("/counter")
public class CounterFlightController {

	private final String JSON = "application/json";
	private final String XML = "application/xml";
	
	
	@Autowired
	private CounterFlightService flightService;
	
	@GetMapping(value ="/flights", produces = { XML, JSON })
	public List<Flight> getAllFlights(@RequestParam(required = false, defaultValue = "100") int size) {		
		return flightService.getAllFlights(size);
	}	
	
	@GetMapping(value = "/flight/{id}", produces = { XML, JSON })
	public ResponseEntity<Flight> getFlightById(@PathVariable Integer id) {
		return flightService.getFlightById(id);								 
	}
	
	@GetMapping(value = "/flight/airport_des/{code}", produces = { JSON, XML })
	public ResponseEntity<List<Flight>> getFlightByDesAirport(@PathVariable String code){
		return flightService.getFlightByDesAirport(code);
	}
	
	@GetMapping(value = "/flight/airport_dep/{code}", produces = { JSON, XML })
	public List<Flight> getFlightByDepAirport(@PathVariable String code){
		return flightService.getFlightByDepAirport(code);
	}
	
	@GetMapping(value = "/flight/time_dep_after/{time}", produces = { JSON, XML })
	public ResponseEntity<List<Flight>> getFlightAfterDepartTime(@PathVariable LocalDateTime time){
		return flightService.getFlightAfterDepartTime(time);
	}
	
	@GetMapping(value = "/flight/time_dep_before/{time}", produces = { JSON, XML })
	public ResponseEntity<List<Flight>> getFlightBeforeDepartTime(@PathVariable LocalDateTime time){
		return flightService.getFlightBeforeBepartTime(time);
	}
	
	@PostMapping(value ="/flight", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.CREATED)
	public Flight createFlight(@Valid @RequestBody Flight flight) {
		return flightService.createFlight(flight);
	}	

	@PutMapping(value ="/flight", produces = { XML, JSON }, consumes = { XML, JSON })
	@ResponseStatus(HttpStatus.OK)
	public Flight updateFlight(@Valid @RequestBody Flight flight) {
		return flightService.updateFlight(flight);
	}	
	
}
