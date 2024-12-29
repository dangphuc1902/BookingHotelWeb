package com.bookinghotel.bookinghotel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@Data
@Entity(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name = "check_in")
    private LocalDate check_in;

    @Column(name = "check_out")
    private LocalDate check_out;

    @Column(name = "adults")
    private int adults;

    @Column(name = "children")
    private int children;

    @Column(name = "total_guest")
    private int totalGuest;

    @Column(name = "confirmation_code")
    private String confirmCode;

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "guest_full_name")
    private String guestFullName;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    public BookingEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookingEntity(LocalDate check_in, LocalDate check_out, int adults, int children, int totalGuest, String confirmCode,
			String guestEmail, String guestFullName, RoomEntity room) {
		super();
		this.check_in = check_in;
		this.check_out = check_out;
		this.adults = adults;
		this.children = children;
		this.totalGuest = totalGuest;
		this.confirmCode = confirmCode;
		this.guestEmail = guestEmail;
		this.guestFullName = guestFullName;
		this.room = room;
	}
}
