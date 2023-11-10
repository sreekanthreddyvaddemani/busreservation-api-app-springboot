package org.jsp.busreservationapiapp.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, name = "from_loc")
	private String from;
	@Column(nullable = false, name = "to_loc")
	private String to;
	@Column(nullable = false)
	private String seat_no;
	@Column(nullable = false)
	private double cost;
	@Column(nullable = false)
	private String time_of_booking;
	@Column(nullable = false)
	private long ticketnumber;
	@Column(nullable = false)
	private int number_of_seats;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private User user;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Bus bus;

}
