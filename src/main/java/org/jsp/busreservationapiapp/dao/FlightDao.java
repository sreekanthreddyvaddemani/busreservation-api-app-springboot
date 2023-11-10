package org.jsp.busreservationapiapp.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.Flight;
import org.jsp.busreservationapiapp.repository.FlightRepository;
import org.jsp.busreservationapiapp.repository.FlightTicketRepository;
import org.jsp.busreservationapiapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FlightDao {
	@Autowired
	private FlightRepository repository;
	
	@Autowired
	private UserRepository Urepository;
	
	@Autowired
	private FlightTicketRepository Trepository;



	public Flight saveFlight(Flight flight) {
		return repository.save(flight);
	}

	public Flight updateFlight(Flight flight) {
		return repository.save(flight);
	}

	public List<Flight> filter(String from, String to, LocalDate dop) {
		    return repository.filter(from, to, dop);
		}
	

	public Optional<Flight> findFlight(int id) {
		return repository.findById(id);
	}

	public List<Flight> findAll() {
		return repository.findAll();
	}



	public String deleteFlight(Flight flight) {
		repository.delete(flight);
	return "BUS deleted";

}

	public Optional<Flight> findFlightByTicket(long flightcode) {
		return repository.findByTicket(flightcode);
	}

	

	
	
	



//	public List<String> filterfrom() {
//		System.out.println("Executed Dao");
//		return repository.filterfrom();
//	}
}
