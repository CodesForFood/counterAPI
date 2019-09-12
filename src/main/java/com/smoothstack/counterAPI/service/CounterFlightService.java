package com.smoothstack.counterAPI.service;

import java.time.LocalDateTime;
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
import com.smoothstack.counterAPI.entity.Flight;

@Service
public class CounterFlightService {
	
	@Autowired
	private CounterFlightDAO flightDAO;
	
	@Transactional
	public List<Flight> getAllFlights(int size) {
		Pageable limit = PageRequest.of(0,size);
		return flightDAO.findAll(limit).getContent();		
	}

	@Transactional
	public ResponseEntity<Flight> getFlightById(Integer id) {
		Optional<Flight> flight = flightDAO.findById(id);		
				
		return !flight.isPresent() ? new ResponseEntity<Flight>(HttpStatus.NOT_FOUND) 
			: new ResponseEntity<Flight>(flight.get(), HttpStatus.OK);						 
	}
	
	@Transactional
	public Flight createFlight(Flight flight) {
		return flightDAO.save(flight);
	}	

	@Transactional
	public Flight updateFlight(Flight flight) {
		return flightDAO.save(flight);
	}

	@Transactional
	public ResponseEntity<Flight> getFlightByDesAirport(String code) {
		return flightDAO.getFlightByDesAirport(code);		
	}	
	
	@Transactional
	public ResponseEntity<Flight> getFlightByDepAirport(String code) {
		return flightDAO.getFlightByDepAirport(code);		
	}

	@Transactional
	public ResponseEntity<Flight> getFlightAfterDepartTime(LocalDateTime time) {
		return flightDAO.getFlightAfterDepartTime(time);
	}

	@Transactional
	public ResponseEntity<Flight> getFlightBeforeBepartTime(LocalDateTime time) {		
		return flightDAO.getFlightBeforeDepartTime(time);
	}	
}
