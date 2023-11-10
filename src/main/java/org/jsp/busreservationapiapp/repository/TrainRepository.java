package org.jsp.busreservationapiapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.jsp.busreservationapiapp.dto.Train;
import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TrainRepository extends JpaRepository<Train, Integer> {

	
	
	@Query("select t from Train t where t.from=?1 and t.to=?2 and t.dop=?3")
	List<Train> findTrains(String from, String to, LocalDate dop);
	
//	@Query("select t.traintickets from Train t where t.id=?1 and t.traintickets.seattype=?2")
//	List<TrainTicket> findTrainBySeat(int id, String seattype);

}
