package org.jsp.busreservationapiapp.controller;

import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Train;

import java.util.List;

import org.jsp.busreservationapiapp.dto.Admin;
import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.Flight;
import org.jsp.busreservationapiapp.service.AdminService;
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
public class AdminController {

	
	@Autowired
	private AdminService service;

	@PostMapping(value = "/admin")
	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(@RequestBody Admin admin) {
		return service.saveAdmin(admin);
	}

	@PutMapping(value = "/admin")
	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(@RequestBody Admin admin) {
		return service.updateAdmin(admin);
	}

	@GetMapping(value = "/admin/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findById(@PathVariable int id) {
		return service.findbyid(id);
	}

	@PostMapping(value = "/admin/verifybyphone")
	public ResponseEntity<ResponseStructure<Admin>> verifyAdmin(@RequestParam long phone,
			@RequestParam String password) {
		System.out.println("exicuted");
		return service.verifyAdmin(phone, password);
	}
	@GetMapping(value="/admin/buses/{admin_id}")
	public ResponseEntity<ResponseStructure<List<Bus>>> adminBus(@PathVariable int admin_id){
		return service.adminBus(admin_id);
	}
	
	@GetMapping(value="/admin/trains/{admin_id}")
	public ResponseEntity<ResponseStructure<List<Train>>> adminTrain(@PathVariable int admin_id){
		return service.adminTrain(admin_id);
	}
	@GetMapping(value="/admin/flights/{admin_id}")
	public ResponseEntity<ResponseStructure<List<Flight>>> adminFlight(@PathVariable int admin_id){
		return service.adminFlight(admin_id);
	}
	
	
	
	
}
