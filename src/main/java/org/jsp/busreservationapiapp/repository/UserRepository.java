package org.jsp.busreservationapiapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.FlightTicket;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.jsp.busreservationapiapp.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.email=?1 and u.password=?2")
	public Optional<User> verifyUser(String email, String password);
	
	@Query("select u.tickets from User u where u.id=?1")
	public List<Ticket> userTickets(int user_id);
	
	@Query("select u.traintickets from User u where u.id=?1")
	public List<TrainTicket> userTrainTickets(int user_id);

	
	@Query("select u.flighttickets from User u where u.id=?1")
	public List<FlightTicket> userFlightTickets(int user_id);

	
	
	

//	
//	@Query("select u from User u where u.email=?1 and u.tickets.id=?2")
//	public String busLoc(String email, int id);

	
	
	
}
