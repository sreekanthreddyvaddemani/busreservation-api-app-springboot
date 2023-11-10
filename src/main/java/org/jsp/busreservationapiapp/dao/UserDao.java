package org.jsp.busreservationapiapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.FlightTicket;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.jsp.busreservationapiapp.dto.User;
import org.jsp.busreservationapiapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
@Repository
public class UserDao {

	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {
		return repository.save(user);

	}

	public User updateUser(User user) {
		return repository.save(user);
	}

	public Optional<User> findbyid(int id) {
		return repository.findById(id);
	}


	public Optional<User> verifyUser(String email, String password) {
		return repository.verifyUser(email, password);
	}

	public List<User> findAll() {
		return repository.findAll();
	}

	public List<Ticket> userTickets(int user_id) {
		return repository.userTickets(user_id);
	}
	public List<TrainTicket> userTrainTickets(int user_id) {
		return repository.userTrainTickets(user_id);
	}

	public List<FlightTicket> userFlightTickets(int user_id) {
		return repository.userFlightTickets(user_id);

	}

//	public String busLoc(String email, int id) {
//		if(repository.busLoc(email,id)!=null) {
//		   return "data Present";
//		}
//		return "data Is Not Present";
//	}




}
