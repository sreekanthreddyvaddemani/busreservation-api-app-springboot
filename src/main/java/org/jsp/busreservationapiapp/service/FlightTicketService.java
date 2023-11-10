package org.jsp.busreservationapiapp.service;

import java.sql.Struct;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dao.FlightDao;
import org.jsp.busreservationapiapp.dao.FlightTicketDao;
import org.jsp.busreservationapiapp.dao.UserDao;
import org.jsp.busreservationapiapp.dto.EmailConfiguration;
import org.jsp.busreservationapiapp.dto.Flight;
import org.jsp.busreservationapiapp.dto.FlightTicket;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.User;
import org.jsp.busreservationapiapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FlightTicketService {

	@Autowired
	private FlightDao flightDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private FlightTicketDao ticketDao;

	@Autowired
	private ReservationApiEmailService service;

	@Autowired
	private EmailConfiguration configuration;

	public ResponseEntity<ResponseStructure<FlightTicket>> saveFlightTicket(FlightTicket ticket, int user_id, int flight_id) {
		Optional<User> recUser = userDao.findbyid(user_id);
		Optional<Flight> recFlight = flightDao.findFlight(flight_id);
		ResponseStructure<FlightTicket> structure = new ResponseStructure<>();
		if (recUser.isPresent() && recFlight.isPresent()) {
			User u = recUser.get();
			Flight b = recFlight.get();
			ticket.setCost(ticket.getNumber_of_seats() * b.getCost_per_seat());
			u.getFlighttickets().add(ticket);
			b.getFlighttickets().add(ticket);
			ticket.setUser(u);
			ticket.setFlight(b);
			structure.setData(ticketDao.saveTicket(ticket));
			configuration.setTo(u.getEmail());
			configuration.setSubject("FlightTicket for "+b.getFrom().toUpperCase()+" - "+b.getTo().toUpperCase()
					);
			configuration.setText(b.getFrom().toUpperCase()+" to "+b.getTo().toUpperCase()+"\n"+
			b.getDop()+","+b.getDeptime()+" - "+b.getDop()+","+b.getArrivaltime()+"\n\n\n"+
					
				"Departure Time :\n"
			+b.getDop()+","+b.getDeptime()
				+"\n\nArrival Time :\n"	+
				b.getDop()+","+b.getArrivaltime()+
				"\n\nDeparts At :\n"+b.getFrom().toUpperCase()+
				"\n\nArrives At :\n"+b.getTo().toUpperCase()+
				"\n\nFlightTicket Number :\n"+ticket.getTicketnumber()+
				"\n\nNo of seats :\n"+ticket.getNumber_of_seats()+
				"\n\nName of Traveller :\n"+u.getName()+
				"\n\nTotal Cost :\n"+ticket.getCost()
			)
			;
			String message = service.sendEmail(configuration);
			flightDao.updateFlight(b);
			userDao.updateUser(u);
			structure.setMessage(message);
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<FlightTicket>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<FlightTicket>> updateFlightTicket(FlightTicket ticket, int user_id, int flight_id) {
		Optional<User> recUser = userDao.findbyid(user_id);
		Optional<Flight> recFlight = flightDao.findFlight(flight_id);
		ResponseStructure<FlightTicket> structure = new ResponseStructure<>();
		if (recUser.isPresent() && recFlight.isPresent()) {
			User u = recUser.get();
			Flight b = recFlight.get();
//			ticket.setCost(ticket.getNumber_of_seats() * b.getCost_per_seat());
			ticket.setUser(u);
			ticket.setFlight(b);
			structure.setData(ticketDao.saveTicket(ticket));
			flightDao.updateFlight(b);
			userDao.updateUser(u);
			structure.setMessage("FlightTicket Updated Successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<FlightTicket>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<FlightTicket>> findbyId(int id) {
		ResponseStructure<FlightTicket> structure = new ResponseStructure<>();
		Optional<FlightTicket> recAdmin = ticketDao.findbyId(id);
		if (recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<FlightTicket>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<String>> deleteFlightTicket(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<FlightTicket> recFlightTicket = ticketDao.findbyId(id);
		if (recFlightTicket.isEmpty()) {
			throw new IdNotFoundException();
		}
		structure.setData(ticketDao.deleteTicket(recFlightTicket.get()));
		structure.setMessage("FlightTicket cancelled");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}


//	public ResponseEntity<ResponseStructure<List<FlightTicket>>> findbyUserId(int user_id) {
//		ResponseStructure<List<FlightTicket>> structure = new ResponseStructure<>();
//	       structure.setData(ticketDao.findbuUserId(user_id));
//	       structure.setMessage("List of Users");
//	       structure.setStatusCode(HttpStatus.OK.value());
//			return new ResponseEntity<ResponseStructure<List<FlightTicket>>>(structure, HttpStatus.OK);
//
//	}

}
