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
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String trainNumber;
	@Column(nullable = false, name = "from_loc")
	private String from;
	@Column(nullable = false, name = "to_loc")
	private String to;
	@Column(nullable = false, name = "date_of_departure")
	private LocalDate dop;
	@Column(nullable = false)
	private String arrivalTime;
	@Column(nullable = false)
	private String depatureTime;
	@Column(nullable = false)
	private String seatOne;	
	
	@Column(nullable = false)
	private int seatOneCost;
	
	@Column(nullable = false)
	private String seatTwo;
	
	@Column(nullable = false)
	private int seatTwoCost;	
	
	@Column(nullable = false)
	private String seatThree;
	
	@Column(nullable = false)
	private int seatThreeCost;	
	
	@Column(nullable = false)
	private String seatFour;
	
	@Column(nullable = false)
	private int seatFourCost;
	
	@Column(nullable = false)
	private long traincode;

	@Column(nullable = false)
	private String travel_img;
	
	@ManyToOne
	@JoinColumn(name="admin_id")
	@JsonIgnore
	private Admin admin;
	
	@OneToMany(mappedBy = "train")
	private List<TrainTicket> traintickets;
	
	
}
