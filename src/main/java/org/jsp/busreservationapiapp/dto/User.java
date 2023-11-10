package org.jsp.busreservationapiapp.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private long phone;
	@Column(name = "date_of_birth")
	private LocalDate dob;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private long adhaar;
	@Column(nullable = false)
	private String password;
	@OneToMany(mappedBy = "user")
	private List<Ticket> tickets;
	@OneToMany(mappedBy = "user")
	private List<TrainTicket> traintickets;
	@OneToMany(mappedBy = "user")
	private List<FlightTicket> flighttickets;
}
