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

import com.smoothstack.counterAPI.dao.CounterBookingDAO;
import com.smoothstack.counterAPI.entity.Booking;

@Service
public class CounterBookingService {

	
	@Autowired
	private CounterBookingDAO bookingDAO;

	@Transactional
	public List<Booking> getAllBookings(int size, int page) {	
		Pageable limit = PageRequest.of(page, size);
		
		return bookingDAO.findAll(limit).getContent();	
	}

	@Transactional
	public ResponseEntity<List<Booking>> getBookingsById(Integer id) {
		Optional<List<Booking>> booking = bookingDAO.getBookingsById(id);		
		
		return !booking.isPresent() ? new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND) 
			: new ResponseEntity<List<Booking>>(booking.get(), HttpStatus.OK);				
	}

	@Transactional
	public ResponseEntity<List<Booking>> getBookingsOfFlight(int page, Integer id) {
		final int size = 50;
		final int min = (page * size) - size;			
		Optional<List<Booking>> booking = bookingDAO.getBookingsOfFlight(id, min, size);
		return booking.isPresent() ? new ResponseEntity<List<Booking>>(booking.get(),HttpStatus.OK)
				: new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
	}

	@Transactional
	public ResponseEntity<List<Booking>> getBookingsOfTraveler(Integer id) {
		Optional<List<Booking>> booking = bookingDAO.getBookingsOfTraveler(id);		
		
		return booking.isPresent() ? new ResponseEntity<List<Booking>>(booking.get(),HttpStatus.OK)
				: new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND);
	}
	
	
}
