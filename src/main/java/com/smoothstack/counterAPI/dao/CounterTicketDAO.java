package com.smoothstack.counterAPI.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.smoothstack.counterAPI.entity.Ticket;

public interface CounterTicketDAO extends JpaRepository<Ticket, Integer> {
	
	@Query(value = "SELECT * FROM utopiaairlines.ticket WHERE traveler_id = ?1", nativeQuery = true)
	public Optional<List<Ticket>> getTicketsOfTravler(Integer id);

	@Query(value = "SELECT * FROM utopiaairlines.ticket WHERE flight_id = ?1", nativeQuery = true)
	public Optional<List<Ticket>> getTicketsOfFlight(Integer id);
	
	@Query(value = "SELECT * FROM utopiaairlines.ticket WHERE travel_agent_id = ?1", nativeQuery = true)
	public Optional<List<Ticket>> getTicketsOfTravelAgent(Integer id);

	@Modifying
	@Query(value = "UPDATE utopiaairlines.flight SET number_of_tickets = number_of_tickets - 1 WHERE flight_id = ?1", nativeQuery = true)
	public int decrementFlightTickets(Integer id);
	
	@Modifying
	@Query(value = "UPDATE utopiaairlines.flight SET number_of_tickets = number_of_tickets + 1 WHERE flight_id = ?1", nativeQuery = true)
	public int incrementFlightTickets(Integer id); 
	
	@Modifying
	@Query(value = "UPDATE utopiaairlines.traveler SET traveler_funds = traveler_funds + (SELECT ticket_price FROM utopiaairlines.flight WHERE flight_id = ?2) WHERE traveler_id = ?1", nativeQuery = true)
	public int refundTravelerFunds(Integer travelerId, Integer flightId);
	
	@Modifying
	@Query(value = "UPDATE utopiaairlines.traveler SET traveler_funds = traveler_funds - (SELECT ticket_price FROM utopiaairlines.flight WHERE flight_id = ?2) WHERE traveler_id = ?1", nativeQuery = true)
	public int chargeTraveler(Integer travelerId, Integer flightId);
	
}
