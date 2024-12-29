package com.bookinghotel.bookinghotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@Entity(name = "rooms")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "room_type")
    private String roomType;
    @Column(name = "room_price")
    private int roomPrice;
    @Column(name = "image")
    private String image;
    @Column(name = "is_booked")
    private boolean isBooked = false;

    public RoomEntity(int id, String roomType, int roomPrice, String image, boolean isBooked,
			List<BookingEntity> bookings) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.image = image;
		this.isBooked = isBooked;
		this.bookings = bookings;
	}
    

	public RoomEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	public void addBooking(BookingEntity booking){
		if (booking == null){
			bookings = new ArrayList<>();
		}
		bookings.add(booking);
        assert booking != null;
        booking.setRoom(this);
		isBooked = true;
		String bookingCode = RandomStringUtils.randomNumeric(10);
		booking.setConfirmCode(bookingCode);
	}


	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private List<BookingEntity> bookings;
}
