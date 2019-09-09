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

import com.smoothstack.counterAPI.dao.CounterDAO;
import com.smoothstack.counterAPI.entity.Counter;

@Service
public class CounterService {
	
	@Autowired
	private CounterDAO counterDAO;
	
	@Transactional
	public List<Counter> getAllCounters(int size) {
		Pageable limit = PageRequest.of(0,size);
		return counterDAO.findAll(limit).getContent();		
	}

	@Transactional
	public ResponseEntity<Counter> getCounterById(Integer id) {
		Optional<Counter> counter = counterDAO.findById(id);		
				
		return !counter.isPresent() ? new ResponseEntity<Counter>(HttpStatus.NOT_FOUND) 
			: new ResponseEntity<Counter>(counter.get(), HttpStatus.OK);						 
	}
	
	@Transactional
	public ResponseEntity<Counter> getCounterByAirport(String code){
		Optional<Counter> counter = counterDAO.getCounterByAirportCode(code);
		
		return !counter.isPresent() ? new ResponseEntity<Counter>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<Counter>(counter.get(), HttpStatus.OK);
	}
}
