package org.jsp.busreservationapiapp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query("select b from Flight b where b.from=?1 and b.to=?2 and b.dop=?3")
	public List<Flight> filter(String from, String to, LocalDate dop);

	
	@Query("select b from Flight b where b.flightcode=?1")
	public Optional<Flight> findByTicket(long flightcode);

	
	
	
	
	
//	@Query("select b.from from Flight b where b.from like='k%'")
//	public List<String> filterfrom();

	

}
