package com.bookinghotel.bookinghotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

public class BookingDTO {
    private int id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numOfAdults;
    private int numOfChildren;
    private int totalNumGuest;
    private String bookingConfirmCode;
    private String fullNameGuest;
    private String emailGuest;
    private RoomDTO roomDTO;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNumOfAdults() {
		return numOfAdults;
	}

	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
	}

	public int getNumOfChildren() {
		return numOfChildren;
	}

	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}

	public int getTotalNumGuest() {
		return totalNumGuest;
	}

	public void setTotalNumGuest(int totalNumGuest) {
		this.totalNumGuest = totalNumGuest;
	}

	public String getBookingConfirmCode() {
		return bookingConfirmCode;
	}

	public void setBookingConfirmCode(String bookingConfirmCode) {
		this.bookingConfirmCode = bookingConfirmCode;
	}

	public String getFullNameGuest() {
		return fullNameGuest;
	}

	public void setFullNameGuest(String fullNameGuest) {
		this.fullNameGuest = fullNameGuest;
	}

	public String getEmailGuest() {
		return emailGuest;
	}

	public void setEmailGuest(String emailGuest) {
		this.emailGuest = emailGuest;
	}

	public RoomDTO getRoomDTO() {
		return roomDTO;
	}

	public void setRoomDTO(RoomDTO roomDTO) {
		this.roomDTO = roomDTO;
	}
	public BookingDTO(int id, LocalDate checkInDate, LocalDate checkOutDate, String bookingConfirmationCode) {
		this.id = id;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.bookingConfirmCode = bookingConfirmationCode;
	}

	public BookingDTO(int id, LocalDate checkInDate, LocalDate checkOutDate, int numOfAdults, int numOfChildren, int totalNumOfGuests, String bookingConfirmationCode, String guestEmail, String guestName, RoomDTO room) {
		this.id = id;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numOfAdults = numOfAdults;
		this.numOfChildren = numOfChildren;
		this.totalNumGuest = totalNumOfGuests;
		this.bookingConfirmCode = bookingConfirmationCode;
		this.emailGuest = guestEmail;
		this.fullNameGuest = guestName;
		this.roomDTO = room;
	}

	public BookingDTO(int id, LocalDate checkInDate, LocalDate checkOutDate, String bookingConfirmationCode, RoomDTO room) {
		this.id = id;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.bookingConfirmCode = bookingConfirmationCode;
		this.roomDTO = room;
	}
//	public BookingDTO(int id, LocalDate checkIn, LocalDate checkOut, String confirmCode) {
//		this.id = id;
//		this.checkInDate = checkIn;
//		this.checkOutDate = checkOut;
//		this.bookingConfirmCode = confirmCode;
//    }
//
//	public BookingDTO(int id, LocalDate check_in, LocalDate check_out, int adults, int children, int totalGuest,
//			String confirmCode, String guestEmail, String guestFullName, RoomDTO roomDto) {
//		// TODO Auto-generated constructor stub
//		this.id = id;
//		this.checkInDate = check_in;
//		this.checkOutDate = check_out;
//		this.numOfAdults = adults;
//		this.numOfChildren = children;
//		this.totalNumGuest = totalGuest;
//		this.bookingConfirmCode = confirmCode;
//		this.fullNameGuest = guestFullName;
//		this.emailGuest = guestEmail;
//		this.roomDTO = roomDto;
//	}

    
}
