package org.jsp.busreservationapiapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dao.AdminDao;
import org.jsp.busreservationapiapp.dao.FlightDao;
import org.jsp.busreservationapiapp.dto.Admin;
import org.jsp.busreservationapiapp.dto.EmailConfiguration;
import org.jsp.busreservationapiapp.dto.Flight;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.exception.IdNotFoundException;
import org.jsp.busreservationapiapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class FlightService {
	@Autowired
	private FlightDao flightDao;
	@Autowired
	private AdminDao adminDao;

	@Autowired
	private ReservationApiEmailService service;

	@Autowired
	private EmailConfiguration configuration;

	public ResponseEntity<ResponseStructure<Flight>> saveFlight(Flight flight, int admin_id) {
		Optional<Admin> recAdmin = adminDao.findById(admin_id);
		ResponseStructure<Flight> structure = new ResponseStructure<>();
		if (recAdmin.isPresent()) {
			Admin a = recAdmin.get();
			a.getFlights().add(flight);
			flight.setAdmin(a);
			adminDao.updateAdmin(a);
//			flightDao.saveFlight(flight);
			structure.setData(flightDao.saveFlight(flight));
			structure.setMessage("Flight Added Successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Flight>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> filter(String from, String to, LocalDate dop) {
		ResponseStructure<List<Flight>> structure = new ResponseStructure<>();
		structure.setData(flightDao.filter(from, to, dop));
		structure.setMessage("List of Flightes");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Flight>>>(structure, HttpStatus.OK);
//		throw new InvalidCredentialsException();

	}

	public ResponseEntity<ResponseStructure<Flight>> updateFlight(Flight flight, int admin_id, int flight_id) {
		Optional<Admin> recAdmin = adminDao.findById(admin_id);
		ResponseStructure<Flight> structure = new ResponseStructure<>();
		if (recAdmin.isPresent()) {
//			Admin a = recAdmin.get();
//			a.getFlightes().add(flight);
//			flight.setAdmin(a);
//			adminDao.updateAdmin(a);
			flight.setId(flight_id);
			flightDao.updateFlight(flight);
			structure.setData(flight);
			structure.setMessage("Flight Updated Successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Flight>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteFlight(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Flight> recFlight = flightDao.findFlight(id);
		if (recFlight.isEmpty()) {
			throw new IdNotFoundException();
		}
		structure.setData(flightDao.deleteFlight(recFlight.get()));
		structure.setMessage("Flight Deleted");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Flight>> findFlight(int id) {
		ResponseStructure<Flight> structure = new ResponseStructure<>();
		Optional<Flight> recAdmin = flightDao.findFlight(id);
		if (recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Flight Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Flight>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> findAll() {
		ResponseStructure<List<Flight>> structure = new ResponseStructure<>();
		structure.setData(flightDao.findAll());
		structure.setMessage("List of Flightes");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Flight>>>(structure, HttpStatus.OK);
	}

	
}
