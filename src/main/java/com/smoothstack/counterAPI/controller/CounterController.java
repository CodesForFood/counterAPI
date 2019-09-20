package com.smoothstack.counterAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.counterAPI.entity.Counter;
import com.smoothstack.counterAPI.service.CounterService;


@RestController
@RequestMapping("/counter")
public class CounterController {

	private final String XML = "application/xml";
	private final String JSON = "application/json";
	
	@Autowired
	private CounterService counterService;
	
	@GetMapping(value ="/listall", produces = { JSON, XML })
	public List<Counter> getAllCounters(@RequestParam(required = false, defaultValue = "100") int size) {		
		return counterService.getAllCounters(size);
	}
	
	@GetMapping(value = "/{id}", produces = { JSON, XML })
	public ResponseEntity<Counter> getCounterById(@PathVariable Integer id) {
		ResponseEntity<Counter> counter = counterService.getCounterById(id); 		
		return counter;
	}
	
	@GetMapping(value="/airport/{code}",produces = { JSON, XML})
	public ResponseEntity<Counter> getCounterByAirportCode(@PathVariable String code){
		return counterService.getCounterByAirport(code);
	}
		
}
