package org.jsp.busreservationapiapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dao.AdminDao;
import org.jsp.busreservationapiapp.dto.Admin;
import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.Flight;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Train;
import org.jsp.busreservationapiapp.exception.IdNotFoundException;
import org.jsp.busreservationapiapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class AdminService {
	@Autowired
	private AdminDao dao;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setData(dao.saveAdmin(admin));
		structure.setMessage("admin Saved Successfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setData(dao.updateAdmin(admin));
		structure.setMessage("Admin Updated Successfully");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);

	}

	public ResponseEntity<ResponseStructure<Admin>> findbyid(int id) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = dao.findbyid(id);
		if (recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Admin>> verifyAdmin(long phone, String password) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = dao.verifyAdmin(phone, password);
		if (recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin verified");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException();
	}

	
	public ResponseEntity<ResponseStructure<List<Bus>>> adminBus(int admin_id) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		structure.setData(dao.adminBus(admin_id));
		structure.setMessage("List of Admin Buses");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Train>>> adminTrain(int admin_id) {
		ResponseStructure<List<Train>> structure = new ResponseStructure<>();
		structure.setData(dao.adminTrain(admin_id));
		structure.setMessage("List of Admin Buses");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Train>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> adminFlight(int admin_id) {
		// TODO Auto-generated method stub
		ResponseStructure<List<Flight>> structure = new ResponseStructure<>();
		structure.setData(dao.adminFlight(admin_id));
		structure.setMessage("List of Admin Buses");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Flight>>>(structure, HttpStatus.OK);
	}
}
