package org.jsp.busreservationapiapp.controller;

import java.util.List;

import org.jsp.busreservationapiapp.dao.TicketDao;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.jsp.busreservationapiapp.service.TicketService;
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
public class TicketController {
	@Autowired
	private TicketService service;
	
	@Autowired
	private TicketDao dao;

	@PostMapping(value="/ticket/{user_id}/{bus_id}")
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(@RequestBody Ticket ticket,@PathVariable int user_id,@PathVariable int bus_id){
		System.out.println(ticket.getTicketnumber());
		return service.saveTicket(ticket, user_id,bus_id);
	}
	
	@PutMapping(value="/ticket/{user_id}/{bus_id}")
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(@RequestBody Ticket ticket,@PathVariable int user_id,@PathVariable int bus_id){
		return service.updateTicket(ticket, user_id,bus_id);
	}
//	@GetMapping(value="/tickets/{bus_id}")
//	public ResponseEntity<ResponseStructure<Ticket>> findbyId(@PathVariable int bus_id){
//		return service.findseatsByBusId(bus_id);
//	}
	
//	@GetMapping(value="/ticket/findbyuserid/{user_id}")
//	public ResponseEntity<ResponseStructure<List<Ticket>>> findbyUserId(@PathVariable int user_id){
//		return service.findbyUserId(user_id);
//	}
	@GetMapping(value="/ticket/{id}")
	public ResponseEntity<ResponseStructure<String>> cancelTicket(@PathVariable int id){
		return service.deleteTicket(id);
	}	
	
	
	
}
