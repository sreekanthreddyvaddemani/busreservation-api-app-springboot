package org.jsp.busreservationapiapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Train;
import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.jsp.busreservationapiapp.service.BusService;
import org.jsp.busreservationapiapp.service.TicketService;
import org.jsp.busreservationapiapp.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
public class TrainController {
	
	@Autowired
	private TrainService service;

	@PostMapping(value="/train/{admin_id}")
	public ResponseEntity<ResponseStructure<Train>> saveBus(@RequestBody Train train,@PathVariable int admin_id){
		return service.saveTrain(train, admin_id);
	}
	
//	@PutMapping(value="/train/{admin_id}/{bus_id}")
//	public ResponseEntity<ResponseStructure<Train>> updateBus(@RequestBody Train train,@PathVariable int admin_id,@PathVariable int train_id){
//		return service.updateTrain(train, admin_id,train_id);
//	}
	
	@GetMapping(value="/train/filter")
	public ResponseEntity<ResponseStructure<List<Train>>> filter(@RequestParam String from,@RequestParam String to,@RequestParam LocalDate dop){
		return service.filtertrain(from,to,dop);
	}
	
	@GetMapping(value="/trains")
	public ResponseEntity<ResponseStructure<List<Train>>> findAll(){
		return service.findAll();
	}
	@GetMapping(value="/train/{id}")
	public ResponseEntity<ResponseStructure<Train>> findTrain(@PathVariable int id){
		return service.findTrain(id);
	}
//	@GetMapping(value="/train/{id}/{seattype}")
//	public ResponseEntity<ResponseStructure<List<TrainTicket>>> findTrainBySeat(@PathVariable int id,@PathVariable String seattype){
//		return service.findTrainBySeat(id,seattype);
//	}
}
