package org.jsp.busreservationapiapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.service.BusService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BusController {

	@Autowired
	private BusService service;

	@PostMapping(value="/bus/{admin_id}")
	public ResponseEntity<ResponseStructure<Bus>> saveBus(@RequestBody Bus bus,@PathVariable int admin_id){
		return service.saveBus(bus, admin_id);
	}
	@PutMapping(value="/bus/{admin_id}/{bus_id}")
	public ResponseEntity<ResponseStructure<Bus>> updateBus(@RequestBody Bus bus,@PathVariable int admin_id,@PathVariable int bus_id){
		return service.updateBus(bus, admin_id,bus_id);
	}

	@GetMapping(value="/buses")
	public ResponseEntity<ResponseStructure<List<Bus>>> findAll(){
		return service.findAll();
	}

	
	@GetMapping(value="/bus/filter")
	public ResponseEntity<ResponseStructure<List<Bus>>> filter(@RequestParam String from,@RequestParam String to,@RequestParam LocalDate dop){
		return service.filter(from,to,dop);
	}
		
	@GetMapping(value="/bus/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBus(@PathVariable int id){
		return service.deleteBus(id);
	}

	
	@GetMapping(value="/bus/{id}")
	public ResponseEntity<ResponseStructure<Bus>> findBus(@PathVariable int id){
		return service.findBus(id);
	}
	
	@GetMapping(value="/bus/buscode")
	public ResponseEntity<ResponseStructure<String>> findBus(@RequestParam long buscode,@RequestParam String email){
		return service.findByBusCode(buscode, email);
	}

	

	
}
