package org.jsp.busreservationapiapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.FlightTicket;
import org.jsp.busreservationapiapp.repository.FlightTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FlightTicketDao {

	@Autowired
	private FlightTicketRepository repository;

	public FlightTicket saveTicket(FlightTicket ticket) {
		return repository.save(ticket);
	}
	
	public Optional<FlightTicket> findbyId(int id) {
		return repository.findById(id);
	}

	public String deleteTicket(FlightTicket ticket) {
			repository.delete(ticket);
		return "ticket Not deleted";

	}

}
