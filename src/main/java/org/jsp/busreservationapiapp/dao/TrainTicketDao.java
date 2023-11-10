package org.jsp.busreservationapiapp.dao;

import java.util.List;

import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.jsp.busreservationapiapp.repository.TrainTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainTicketDao {
	@Autowired
	private TrainTicketRepository repository;

	public TrainTicket saveTicket(TrainTicket ticket) {
		System.out.println("EXECUTED");
		return repository.save(ticket);
	}

	public List<TrainTicket> findTrainTicketsBySeat(String seattype) {
		
		return repository.findTrainTicketsBySeat(seattype);
	}

}
