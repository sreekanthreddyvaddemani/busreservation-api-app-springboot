package org.jsp.busreservationapiapp.controller;

import java.util.List;

import org.jsp.busreservationapiapp.dao.TicketDao;
import org.jsp.busreservationapiapp.dao.TrainTicketDao;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.jsp.busreservationapiapp.service.TicketService;
import org.jsp.busreservationapiapp.service.TrainTicketService;
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
public class TrainTicketController {

	

	@Autowired
	private TrainTicketService service;
	
	@Autowired
	private TrainTicketDao dao;

	@PostMapping(value="train/trainticket/{user_id}/{train_id}")
	public ResponseEntity<ResponseStructure<TrainTicket>> saveTicket(@RequestBody TrainTicket ticket,@PathVariable int user_id,@PathVariable int train_id){
		System.out.println(ticket.getTicketnumber());
		return service.saveTrainTicket(ticket, user_id,train_id);
	}

	
	@GetMapping(value="/train/trainticket/{seattype}")
	public ResponseEntity<ResponseStructure<List<TrainTicket>>> findbyUserId(@PathVariable String seattype){
		return service.findTrainTicketsBySeat(seattype);
	}
	
}
