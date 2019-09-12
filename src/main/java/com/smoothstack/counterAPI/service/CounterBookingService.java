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
	public List<Booking> getAllBookings(int size) {
		Pageable limit = PageRequest.of(0,size);
		return bookingDAO.findAll(limit).getContent();		
	}

	@Transactional
	public ResponseEntity<Booking> getBookingById(Integer id) {
		Optional<Booking> booking = bookingDAO.findById(id);		
				
		return !booking.isPresent() ? new ResponseEntity<Booking>(HttpStatus.NOT_FOUND) 
			: new ResponseEntity<Booking>(booking.get(), HttpStatus.OK);						 
	}
	
	@Transactional
	public ResponseEntity<List<Booking>> getBookingsOfTraveler(Integer id){
		Optional<List<Booking>> booking = bookingDAO.getBookingsOfTravler(id);
		
		return !booking.isPresent() ? new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND) 
				: new ResponseEntity<List<Booking>>(booking.get(), HttpStatus.OK);			
	}
	
	@Transactional
	public ResponseEntity<List<Booking>> getBookingsOfFlight(Integer id){
		Optional<List<Booking>> booking = bookingDAO.getBookingsOfFlight(id);
		
		return !booking.isPresent() ? new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND) 
				: new ResponseEntity<List<Booking>>(booking.get(), HttpStatus.OK);			
	}
	
	@Transactional
	public ResponseEntity<List<Booking>> getBookingsOfTravelAgent(Integer id){
		Optional<List<Booking>> booking = bookingDAO.getBookingsOfTravelAgent(id);
		
		return !booking.isPresent() ? new ResponseEntity<List<Booking>>(HttpStatus.NOT_FOUND) 
				: new ResponseEntity<List<Booking>>(booking.get(), HttpStatus.OK);			
	}	
	
	@Transactional
	public ResponseEntity<Booking> createBooking(Booking booking) {
		final double price = booking.getFlight().getTicketPrice();
		final double funds = booking.getTraveler().getFunds();
		
		if(funds >= price) {
			Booking savedBooking = bookingDAO.save(booking);
			bookingDAO.decrementFlightTickets(savedBooking.getFlight().getId());
			bookingDAO.chargeTraveler(booking.getTraveler().getId(), booking.getFlight().getId());
			return new ResponseEntity<Booking>(savedBooking, HttpStatus.CREATED);
		}
		else{
			return new ResponseEntity<Booking>(HttpStatus.PAYMENT_REQUIRED);
		}							
	}	

	@Transactional
	public Booking updateBooking(Booking booking) {
		return bookingDAO.save(booking);
	}
	
	@Transactional
	public boolean cancleBooking(Booking booking) {
		try {
			bookingDAO.deleteById(booking.getId());	
			bookingDAO.incrementFlightTickets(booking.getFlight().getId());
			bookingDAO.refundTravelerFunds(booking.getTraveler().getId(), booking.getFlight().getId());
			return true;
		}
		catch(Exception ex){	
			return false;			
		}
	}

}
