package org.jsp.busreservationapiapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dao.AdminDao;
import org.jsp.busreservationapiapp.dao.BusDao;
import org.jsp.busreservationapiapp.dto.Admin;
import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.EmailConfiguration;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.jsp.busreservationapiapp.dto.User;
import org.jsp.busreservationapiapp.exception.IdNotFoundException;
import org.jsp.busreservationapiapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	@Autowired
	private BusDao busDao;
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ReservationApiEmailService service;
	
	@Autowired
	private EmailConfiguration configuration;
	

	public ResponseEntity<ResponseStructure<Bus>> saveBus(Bus bus, int admin_id) {
		Optional<Admin> recAdmin = adminDao.findById(admin_id);
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		if (recAdmin.isPresent()) {
			Admin a = recAdmin.get();
			a.getBuses().add(bus);
			bus.setAdmin(a);
			adminDao.updateAdmin(a);
//			busDao.saveBus(bus);
			structure.setData(busDao.saveBus(bus));
			structure.setMessage("Bus Added Successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	
	
	
	public ResponseEntity<ResponseStructure<List<Bus>>> filter(String from, String to, LocalDate dop) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		structure.setData(busDao.filter(from, to, dop));
		structure.setMessage("List of Buses");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.OK);
//		throw new InvalidCredentialsException();

	}

	public ResponseEntity<ResponseStructure<Bus>> updateBus(Bus bus, int admin_id, int bus_id) {
		Optional<Admin> recAdmin = adminDao.findById(admin_id);
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		if (recAdmin.isPresent()) {
//			Admin a = recAdmin.get();
//			a.getBuses().add(bus);
//			bus.setAdmin(a);
//			adminDao.updateAdmin(a);
			bus.setId(bus_id);
			busDao.updateBus(bus);
			structure.setData(bus);
			structure.setMessage("Bus Updated Successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteBus(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Bus> recBus = busDao.findBus(id);
		if (recBus.isEmpty()) {
			throw new IdNotFoundException();
		}
		structure.setData(busDao.deleteBus(recBus.get()));
		structure.setMessage("Bus Deleted");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}

	
	
	
	public ResponseEntity<ResponseStructure<Bus>> findBus(int id) {
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		Optional<Bus> recAdmin = busDao.findBus(id);
		if (recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Bus>>> findAll() {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		structure.setData(busDao.findAll());
		structure.setMessage("List of Buses");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.OK);
	}



	public ResponseEntity<ResponseStructure<String>> findByBusCode(long buscode,String email) {
		Optional<Bus> recBus = busDao.findBusByTicket(buscode);
		ResponseStructure<String> structure = new ResponseStructure<>();
		Bus b = recBus.get();
		if (b.getBuscode()==buscode) {
			configuration.setTo(email);
			configuration.setSubject("Track Your Ticket");
			configuration.setText(b.getLocation());
			String message = service.sendEmail(configuration);
			structure.setData("Location Sended t your mail");
			structure.setMessage(message);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException();
	}
}
