package com.smoothstack.counterAPI.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smoothstack.counterAPI.entity.Booking;

public interface CounterBookingDAO extends JpaRepository<Booking, Integer>{

	@Query(value="SELECT * FROM utopiaairlines.booking WHERE booking_id = id", nativeQuery = true)
	Optional<List<Booking>> getBookingsById(Integer id);

	@Query(value="SELECT * FROM utopiaairlines.booking WHERE ticket_id = (SELECT ticket_id FROM utopiaairlines.ticket WHERE flight_id = ?1) LIMIT ?2, ?3", nativeQuery = true)
	Optional<List<Booking>> getBookingsOfFlight(Integer id, int min, int size);

	@Query(value="SELECT * FROM utopiaairlines.booking WHERE ticket_id = (SELECT ticket_id FROM utopiaairlines.ticket WHERE traveler_id = ?1)", nativeQuery = true)
	Optional<List<Booking>> getBookingsOfTraveler(Integer id);

}
