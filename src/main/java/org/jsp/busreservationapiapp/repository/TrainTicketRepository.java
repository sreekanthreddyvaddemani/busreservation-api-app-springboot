package org.jsp.busreservationapiapp.repository;

import java.util.List;

import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainTicketRepository extends JpaRepository<TrainTicket, Integer> {

	
	
	@Query("select t from TrainTicket t where t.seattype=?1")
	List<TrainTicket> findTrainTicketsBySeat(String seattype);

}
