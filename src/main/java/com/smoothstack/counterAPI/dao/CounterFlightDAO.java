package com.smoothstack.counterAPI.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.smoothstack.counterAPI.entity.Flight;

public interface CounterFlightDAO extends JpaRepository<Flight, Integer> {

	@Query(value="SELECT * FROM flight WHERE airport_des_code = ?1", nativeQuery = true)
	public ResponseEntity<List<Flight>> getFlightByDesAirport(String code);
	
	@Query(value="SELECT * FROM flight WHERE airport_dep_code = ?1", nativeQuery = true)
	public List<Flight> getFlightByDepAirport(String code);

	@Query(value="SELECT * FROM flight WHERE flight_dep_time >= ?1", nativeQuery = true)
	public ResponseEntity<List<Flight>> getFlightAfterDepartTime(LocalDateTime time);

	@Query(value="SELECT * FROM flight WHERE flight_dep_time <= ?1", nativeQuery = true)
	public ResponseEntity<List<Flight>> getFlightBeforeDepartTime(LocalDateTime time);

}
