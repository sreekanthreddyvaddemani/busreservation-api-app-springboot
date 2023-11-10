package org.jsp.busreservationapiapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dao.UserDao;
import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.FlightTicket;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.jsp.busreservationapiapp.dto.User;
import org.jsp.busreservationapiapp.exception.IdNotFoundException;
import org.jsp.busreservationapiapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao dao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.saveUser(user));
		structure.setMessage("user Saved Successfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.updateUser(user));
		structure.setMessage("user Updated Successfully");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.ACCEPTED);
	}

	

	public ResponseEntity<ResponseStructure<User>> findbyid(int id) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = dao.findbyid(id);
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("user Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(String email, String password) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = dao.verifyUser(email,password);
		if (recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("user verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException();
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
	       structure.setData(dao.findAll());
	       structure.setMessage("List of Users");
	       structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
//	throw new InvalidCredentialsException();

	}

	public ResponseEntity<ResponseStructure<List<Ticket>>> userTickets(int user_id) {
		ResponseStructure<List<Ticket>> structure = new ResponseStructure<>();
	       structure.setData(dao.userTickets(user_id));
	       structure.setMessage("List of Tickets");
	       structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Ticket>>>(structure, HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<TrainTicket>>> userTrainTickets(int user_id) {
		ResponseStructure<List<TrainTicket>> structure = new ResponseStructure<>();
	       structure.setData(dao.userTrainTickets(user_id));
	       structure.setMessage("List of Tickets");
	       structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<TrainTicket>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<FlightTicket>>> userFlightTickets(int user_id) {
		ResponseStructure<List<FlightTicket>> structure = new ResponseStructure<>();
	       structure.setData(dao.userFlightTickets(user_id));
	       structure.setMessage("List of Tickets");
	       structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<FlightTicket>>>(structure, HttpStatus.OK);
	}





}
