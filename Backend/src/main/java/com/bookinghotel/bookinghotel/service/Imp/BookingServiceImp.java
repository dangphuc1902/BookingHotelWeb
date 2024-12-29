package com.bookinghotel.bookinghotel.service.Imp;

import java.util.List;
import com.bookinghotel.bookinghotel.dto.BookingDTO;
import com.bookinghotel.bookinghotel.entity.BookingEntity;

public interface BookingServiceImp {
	List<BookingDTO> getAllBookings();
	String saveBooking(int id, BookingEntity bookingRequest);
	BookingDTO getBookingByConfirmationCode(String confirmationCode);
	List<BookingDTO> getBookingByEmail(String email);
	boolean deleteBooking(int bookingId);
}
	