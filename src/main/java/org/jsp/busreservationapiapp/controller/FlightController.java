package org.jsp.busreservationapiapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.jsp.busreservationapiapp.dto.Flight;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin
public class FlightController {
	

		@Autowired
		private FlightService service;

		@PostMapping(value="/flight/{admin_id}")
		public ResponseEntity<ResponseStructure<Flight>> saveFlight(@RequestBody Flight flight,@PathVariable int admin_id){
			return service.saveFlight(flight, admin_id);
		}
		@PutMapping(value="/flight/{admin_id}/{flight_id}")
		public ResponseEntity<ResponseStructure<Flight>> updateflight(@RequestBody Flight flight,@PathVariable int admin_id,@PathVariable int flight_id){
			return service.updateFlight(flight, admin_id,flight_id);
		}

		@GetMapping(value="/flights")
		public ResponseEntity<ResponseStructure<List<Flight>>> findAll(){
			return service.findAll();
		}

		
		@GetMapping(value="/flight/filter")
		public ResponseEntity<ResponseStructure<List<Flight>>> filter(@RequestParam String from,@RequestParam String to,@RequestParam LocalDate dop){
			return service.filter(from,to,dop);
		}
			
		@GetMapping(value="/flight/delete/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteFlight(@PathVariable int id){
			return service.deleteFlight(id);
		}

		
		@GetMapping(value="/flight/{id}")
		public ResponseEntity<ResponseStructure<Flight>> findflight(@PathVariable int id){
			return service.findFlight(id);
		}
		
			
	
	
}
