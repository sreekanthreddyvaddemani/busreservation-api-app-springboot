package org.jsp.busreservationapiapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.jsp.busreservationapiapp.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TicketDao {

	@Autowired
	private TicketRepository repository;

	public Ticket saveTicket(Ticket ticket) {
		return repository.save(ticket);
	}
	
	public Optional<Ticket> findbyId(int id) {
		return repository.findById(id);
	}

	public String deleteTicket(Ticket ticket) {
			repository.delete(ticket);
		return "ticket Not deleted";

	}

	
//	public List<Ticket> findbuUserId(int user_id) {
//		return repository.findbyUserId(user_id);
//	}
//	
//	public boolean delete(int id) {
//		Optional<Bus> b=repository.delete(id);
//		if(b!=null) {
//		   repository.deleteById(id);
//		   return true;
//		}
//		return false;
//	}
//


}
