package com.bookinghotel.bookinghotel.controller;

import com.bookinghotel.bookinghotel.dto.BookingDTO;
import com.bookinghotel.bookinghotel.entity.BookingEntity;
import com.bookinghotel.bookinghotel.exception.InvalidBookingRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bookinghotel.bookinghotel.payload.response.BaseResponse;
import com.bookinghotel.bookinghotel.service.Imp.BookingServiceImp;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingServiceImp bookingServiceImp;
	
	@GetMapping("/all-bookings")
	public ResponseEntity<?> getAllBooking(){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(bookingServiceImp.getAllBookings());
		return new ResponseEntity<>(baseResponse, HttpStatus.OK);
	}
	@PostMapping("/add/{id}")
	public ResponseEntity<?> saveBooking(@PathVariable int id, @RequestBody BookingEntity bookingRequest){
		try {
			String CodeBooking = bookingServiceImp.saveBooking(id,bookingRequest);
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setStatus(200);
			baseResponse.setMessage("Room booked successfully, Your booking confirmation code is :");
			baseResponse.setData(CodeBooking);
			return new ResponseEntity<>(baseResponse,HttpStatus.OK);
		}catch (InvalidBookingRequestException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	@GetMapping("/confirmation/{confirmationCode}")
	public ResponseEntity<?>getBookingConfirmationCode(@PathVariable String confirmationCode){
		try {
			BaseResponse baseResponse = new BaseResponse();
			baseResponse.setData(bookingServiceImp.getBookingByConfirmationCode(confirmationCode));
			return ResponseEntity.ok(baseResponse);
		}catch (InvalidBookingRequestException e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	// function Booking History
	@GetMapping("/user/{email}")
	public ResponseEntity<?> getBookingByUserEmail(@PathVariable String email){
		List<BookingDTO> bookingDTO = bookingServiceImp.getBookingByEmail(email);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(bookingDTO);
		baseResponse.setMessage("User booking history:");
		return ResponseEntity.ok(baseResponse);
	}
	// Delete booking:
	@DeleteMapping("/delete/{bookingId}")
	public ResponseEntity<?> cancelBooking(@PathVariable int bookingId){
		boolean cancelBooking = bookingServiceImp.deleteBooking(bookingId);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setMessage(cancelBooking ? "Booking has been canceled successfully! " : "Booking has been cancelled failed!");
		return ResponseEntity.ok(baseResponse);
	}
}
