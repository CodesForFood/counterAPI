package com.smoothstack.counterAPI.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.smoothstack.counterAPI.entity.Flight;

public interface CounterFlightDAO extends JpaRepository<Flight, Integer> {

	@Query(value="SELECT * FROM flight WHERE airport_des_code = ?1", nativeQuery = true)
	public ResponseEntity<Flight> getFlightByDesAirport(String code);
	
	@Query(value="SELECT * FROM flight WHERE airport_dep_code = ?1", nativeQuery = true)
	public ResponseEntity<Flight> getFlightByDepAirport(String code);

}
