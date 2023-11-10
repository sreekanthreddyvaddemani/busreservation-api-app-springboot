package org.jsp.busreservationapiapp.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, name = "from_loc")
	private String from;
	@Column(nullable = false, name = "to_loc")
	private String to;
	@Column(nullable = false)
	private String flight_no;
	@Column(nullable = false, name = "date_of_departure")
	private LocalDate dop;
	@Column(nullable = false, name = "no_of_seats")
	private int seats;
	@Column(nullable = false)
	private long flightcode;
	@Column(nullable = false,name = "time_of_departure")
	private String deptime;
	@Column(nullable = false,name = "time_of_arrival")
	private String arrivaltime;
	@Column(nullable = false)
	private String travel_img;
	@Column(nullable = false)
	private double cost_per_seat;
	@ManyToOne
	@JoinColumn(name="admin_id")
	@JsonIgnore
	private Admin admin;
	@OneToMany(mappedBy = "flight")
	private List<FlightTicket> flighttickets;
}

