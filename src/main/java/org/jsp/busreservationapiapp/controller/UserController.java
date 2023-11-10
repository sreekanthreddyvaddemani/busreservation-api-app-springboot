package org.jsp.busreservationapiapp.controller;

import java.util.List;

import org.jsp.busreservationapiapp.dao.UserDao;
import org.jsp.busreservationapiapp.dto.*;
import org.jsp.busreservationapiapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	private UserService service;
	
	
	@Autowired
	private UserDao dao;

	@PostMapping(value = "/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@PutMapping(value = "/users")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<ResponseStructure<User>> findById(@PathVariable int id) {
		return service.findbyid(id);
	}

	@PostMapping(value = "/users/verifybyemail")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestParam String email,
			@RequestParam String password) {
		return service.verifyUser(email, password);
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/users/tickets/{user_id}")
	public ResponseEntity<ResponseStructure<List<Ticket>>> userTickets(@PathVariable int user_id) {
		return service.userTickets(user_id);
	}
	@GetMapping(value = "/users/traintickets/{user_id}")
	public ResponseEntity<ResponseStructure<List<TrainTicket>>> userTrainTickets(@PathVariable int user_id) {
		return service.userTrainTickets(user_id);
	}
	@GetMapping(value = "/users/flightbookinglist/{user_id}")
	public ResponseEntity<ResponseStructure<List<FlightTicket>>> userFlightTickets(@PathVariable int user_id) {
		return service.userFlightTickets(user_id);
	}

	
	
	

	
//	@DeleteMapping(value = "/users/{id}")
//	public ResponseEntity<ResponseStructure<String>> deleteTicket(@PathVariable int id) {
//		return service.deleteTicket(id);
//	}

//	
//	@GetMapping(value = "/users/loc")
//	public String busLoc(@RequestParam String email,@RequestParam int id) {
//		return dao.busLoc(email,id);
//	}
//


}
