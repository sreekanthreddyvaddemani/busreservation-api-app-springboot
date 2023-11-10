package org.jsp.busreservationapiapp.controller;

import java.util.List;

import org.jsp.busreservationapiapp.dao.FlightTicketDao;
import org.jsp.busreservationapiapp.dto.FlightTicket;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.service.FlightTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FlightTicketController {
	@Autowired
	private FlightTicketService service;
	
	@Autowired
	private FlightTicketDao dao;

	@PostMapping(value="/flightticket/{user_id}/{flight_id}")
	public ResponseEntity<ResponseStructure<FlightTicket>> saveTicket(@RequestBody FlightTicket ticket,@PathVariable int user_id,@PathVariable int flight_id){
		return service.saveFlightTicket(ticket, user_id,flight_id);
	}
	
	@PutMapping(value="/flightticket/{user_id}/{flight_id}")
	public ResponseEntity<ResponseStructure<FlightTicket>> updateTicket(@RequestBody FlightTicket ticket,@PathVariable int user_id,@PathVariable int flight_id){
		return service.updateFlightTicket(ticket, user_id,flight_id);
	}
//	@GetMapping(value="/tickets/{flight_id}")
//	public ResponseEntity<ResponseStructure<Ticket>> findbyId(@PathVariable int flight_id){
//		return service.findseatsByFlightId(flight_id);
//	}
	
//	@GetMapping(value="/ticket/findbyuserid/{user_id}")
//	public ResponseEntity<ResponseStructure<List<Ticket>>> findbyUserId(@PathVariable int user_id){
//		return service.findbyUserId(user_id);
//	}
//	@GetMapping(value="/ticket/{id}")
//	public ResponseEntity<ResponseStructure<String>> cancelTicket(@PathVariable int id){
//		return service.deleteFlightTicket(id);
//	}
	
	
	
}
