package com.smoothstack.counterAPI.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smoothstack.counterAPI.entity.Booking;

public interface CounterBookingDAO extends JpaRepository<Booking, Integer> {
	
	@Query(value = "SELECT * FROM utopiaairlines.booking WHERE traveler_id = ?1", nativeQuery = true)
	public Optional<List<Booking>> getBookingsOfTravler(Integer id);

	@Query(value = "SELECT * FROM utopiaairlines.booking WHERE flight_id = ?1", nativeQuery = true)
	public Optional<List<Booking>> getBookingsOfFlight(Integer id);
	
	@Query(value = "SELECT * FROM utopiaairlines.booking WHERE travel_agent_id = ?1", nativeQuery = true)
	public Optional<List<Booking>> getBookingsOfTravelAgent(Integer id);
	
	@Query(value = "UPDATE utopiaairlines.flight SET number_of_tickets = number_of_tickets - 1 WHERE flight_id = ?1", nativeQuery = true)
	public boolean decrementFlightTickets(Integer id);
	
	@Query(value = "UPDATE utopiaairlines.flight SET number_of_tickets = number_of_tickets + 1 WHERE flight_id = ?1", nativeQuery = true)
	public boolean incrementFlightTickets(Integer id); 
	
}
