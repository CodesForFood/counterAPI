package com.smoothstack.counterAPI.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smoothstack.counterAPI.entity.Counter;


public interface CounterDAO extends JpaRepository<Counter, Integer>{

	
	@Query(value = "SELECT * FROM utopiaairlines.airline_counter WHERE airport_code = ?1", nativeQuery = true)
	public Optional<Counter> getCounterByAirportCode(String code);
}
