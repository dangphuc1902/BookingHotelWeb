package com.bookinghotel.bookinghotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookinghotel.bookinghotel.entity.RoomEntity;
import com.bookinghotel.bookinghotel.exception.InvalidBookingRequestException;
import com.bookinghotel.bookinghotel.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bookinghotel.bookinghotel.dto.BookingDTO;
import com.bookinghotel.bookinghotel.dto.RoomDTO;
import com.bookinghotel.bookinghotel.entity.BookingEntity;
import com.bookinghotel.bookinghotel.repository.BookingRepository;
import com.bookinghotel.bookinghotel.service.Imp.BookingServiceImp;

@Service
public class BookingService implements BookingServiceImp {

	@Autowired
    private BookingRepository bookingRepository;
	@Autowired
	private RoomService roomService;
	@Override
	public List<BookingDTO> getAllBookings() {
		List<BookingDTO> bookingDTOs = new ArrayList<>();
		List<BookingEntity> bookingEntities = bookingRepository.findAll();
		bookingEntities.forEach(booking -> {
			BookingDTO bookingDTO = getBookingDTO(booking);
			bookingDTOs.add(bookingDTO);
		});
		return bookingDTOs;
	}

	@Override
	public String saveBooking(int id, BookingEntity bookingRequest) {
		if(bookingRequest.getCheck_out().isBefore(bookingRequest.getCheck_in())){
			throw new InvalidBookingRequestException("Check in and check out times are unreasonable !");
		}
		RoomEntity roomEntity = roomService.roomById(id);
		List<BookingEntity> existingBookings = roomEntity.getBookings();
		boolean roomIsAvailable = roomIsAvailable(bookingRequest, existingBookings);
		if (roomIsAvailable){
			roomEntity.addBooking(bookingRequest);
			bookingRepository.save(bookingRequest);
		}else{
			throw new InvalidBookingRequestException("Sorry, This rooom is not available for the selected dates;");
		}
		return bookingRequest.getConfirmCode();
	}

	@Override
	public BookingDTO getBookingByConfirmationCode(String confirmationCode) {
		BookingEntity bookingEntity = bookingRepository.findByconfirmCode(confirmationCode)
				.orElseThrow(() -> new ResourceNotFoundException("No booking found with booking code :" + confirmationCode));
		BookingDTO bookingDTO = getBookingDTO(bookingEntity);
		return bookingDTO;
	}

	@Override
	public List<BookingDTO> getBookingByEmail(String email) {
		List<BookingEntity> bookingEntities = bookingRepository.findByGuestEmail(email);
		List<BookingDTO> bookingDTOList = bookingEntities.stream().map(
				bookingEntity ->
						new BookingDTO(
								bookingEntity.getId(),
								bookingEntity.getCheck_in(),
								bookingEntity.getCheck_out(),
								bookingEntity.getAdults(),
								bookingEntity.getChildren(),
								bookingEntity.getTotalGuest(),
								bookingEntity.getConfirmCode(),
								bookingEntity.getGuestEmail(),
								bookingEntity.getGuestFullName(),
								new RoomDTO(
								bookingEntity.getRoom().getId(),
								bookingEntity.getRoom().getRoomType(),
								bookingEntity.getRoom().getRoomPrice())
						)).toList();
		return bookingDTOList;
	}

	@Override
	public boolean deleteBooking(int bookingId) {
		boolean isSuccess = false;
		Optional<BookingEntity> bookingEntity = bookingRepository.findById(bookingId);
		if (bookingEntity.isPresent()){
			bookingRepository.deleteById(bookingId);
			isSuccess = true;
		}
		return isSuccess;
	}

	//	The roomIsAvailable function checks whether a bookingRequest overlaps in time with any booked rooms in the bookingEntities list.
	private boolean roomIsAvailable(BookingEntity bookingRequest, List<BookingEntity> bookingEntities) {
		return bookingEntities.stream()
				.noneMatch(existingBooking ->
								bookingRequest.getCheck_in().equals(existingBooking.getCheck_in())
								|| bookingRequest.getCheck_out().isBefore(existingBooking.getCheck_out())
								|| (bookingRequest.getCheck_in().isAfter(existingBooking.getCheck_in()) && bookingRequest.getCheck_out().isBefore(existingBooking.getCheck_out()))
								|| (bookingRequest.getCheck_in().isBefore(existingBooking.getCheck_in()) && bookingRequest.getCheck_out().equals(existingBooking.getCheck_out()))
								|| (bookingRequest.getCheck_in().isBefore(existingBooking.getCheck_in()) && bookingRequest.getCheck_out().isAfter(existingBooking.getCheck_out()))
								|| (bookingRequest.getCheck_out().equals(existingBooking.getCheck_out()) && bookingRequest.getCheck_in().equals(existingBooking.getCheck_in()))
				);
	}
	public BookingDTO getBookingDTO(BookingEntity booking) {
		RoomDTO roomDto = new RoomDTO(
				booking.getRoom().getId(),
				booking.getRoom().getRoomType(),
				booking.getRoom().getRoomPrice());
		return new BookingDTO(
                booking.getId(),
                booking.getCheck_in(),
                booking.getCheck_out(),
                booking.getAdults(),
                booking.getChildren(),
                booking.getTotalGuest(),
                booking.getConfirmCode(),
                booking.getGuestEmail(),
                booking.getGuestFullName(),
                roomDto
        );
	}
	
}
