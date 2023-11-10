package org.jsp.busreservationapiapp.dto;

import java.util.List;

import org.aspectj.weaver.tools.Trace;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = true)
	private String name;
	@Column(nullable = true, unique = true)
	private long phone;
	@Column(nullable = true, unique = true)
	private String email;
	@Column(nullable = true)
	private String gst;
	@Column(nullable = true)
	private String password;
	@OneToMany(mappedBy = "admin")
	private List<Bus> buses;
	
	@OneToMany(mappedBy = "admin")
	private List<Train> trains;
	
	@OneToMany(mappedBy = "admin")
	private List<Flight> flights;
}
