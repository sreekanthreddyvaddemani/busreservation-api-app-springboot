package org.jsp.busreservationapiapp.service;

import java.sql.Struct;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dao.AdminDao;
import org.jsp.busreservationapiapp.dao.BusDao;
import org.jsp.busreservationapiapp.dao.TicketDao;
import org.jsp.busreservationapiapp.dao.UserDao;
import org.jsp.busreservationapiapp.dto.Admin;
import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.EmailConfiguration;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.jsp.busreservationapiapp.dto.Train;
import org.jsp.busreservationapiapp.dto.User;
import org.jsp.busreservationapiapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

	@Autowired
	private BusDao busDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private ReservationApiEmailService service;

	@Autowired
	private EmailConfiguration configuration;

	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket, int user_id, int bus_id) {
		Optional<User> recUser = userDao.findbyid(user_id);
		Optional<Bus> recBus = busDao.findBus(bus_id);
		ResponseStructure<Ticket> structure = new ResponseStructure<>();
		if (recUser.isPresent() && recBus.isPresent()) {
			User u = recUser.get();
			Bus b = recBus.get();
			ticket.setCost(ticket.getNumber_of_seats() * b.getCost_per_seat());
			u.getTickets().add(ticket);
			b.getTickets().add(ticket);
			ticket.setUser(u);
			ticket.setBus(b);
			configuration.setTo(u.getEmail());
			configuration.setSubject("Ticket for "+b.getFrom().toUpperCase()+" - "+b.getTo().toUpperCase()
					);
			configuration.setText(b.getFrom().toUpperCase()+" to "+b.getTo().toUpperCase()+"\n"+
			b.getDop()+","+b.getDeptime()+" - "+b.getDop()+","+b.getArrivaltime()+"\n\n\n"+
					
				"Departure Time :\n"
			+b.getDop()+","+b.getDeptime()
				+"\n\nArrival Time :\n"	+
				b.getDop()+","+b.getArrivaltime()+
				"\n\nDeparts At :\n"+b.getFrom().toUpperCase()+
				"\n\nArrives At :\n"+b.getTo().toUpperCase()+
				"\n\nTicket Number :\n"+ticket.getTicketnumber()+
				"\n\nNo of seats :\n"+ticket.getNumber_of_seats()+
				"\n\nSeat Number :\n"+ticket.getSeat_no()+
				"\n\nName of Traveller :\n"+u.getName()+
				"\n\nBus Service Number :\n"+b.getBus_no()+
				"\n\nTotal Cost :\n"+ticket.getCost()
			)
			;
			String message = service.sendEmail(configuration);
			structure.setData(ticketDao.saveTicket(ticket));
			busDao.updateBus(b);
			userDao.updateUser(u);
			structure.setMessage(message);
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket, int user_id, int bus_id) {
		Optional<User> recUser = userDao.findbyid(user_id);
		Optional<Bus> recBus = busDao.findBus(bus_id);
		ResponseStructure<Ticket> structure = new ResponseStructure<>();
		if (recUser.isPresent() && recBus.isPresent()) {
			User u = recUser.get();
			Bus b = recBus.get();
//			ticket.setCost(ticket.getNumber_of_seats() * b.getCost_per_seat());
			ticket.setUser(u);
			ticket.setBus(b);
			structure.setData(ticketDao.saveTicket(ticket));
			busDao.updateBus(b);
			userDao.updateUser(u);
			structure.setMessage("Ticket Updated Successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Ticket>> findbyId(int id) {
		ResponseStructure<Ticket> structure = new ResponseStructure<>();
		Optional<Ticket> recAdmin = ticketDao.findbyId(id);
		if (recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Admin Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<String>> deleteTicket(int id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Ticket> recTicket = ticketDao.findbyId(id);
		if (recTicket.isEmpty()) {
			throw new IdNotFoundException();
		}
		structure.setData(ticketDao.deleteTicket(recTicket.get()));
		structure.setMessage("Ticket cancelled");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}


//	public ResponseEntity<ResponseStructure<List<Ticket>>> findbyUserId(int user_id) {
//		ResponseStructure<List<Ticket>> structure = new ResponseStructure<>();
//	       structure.setData(ticketDao.findbuUserId(user_id));
//	       structure.setMessage("List of Users");
//	       structure.setStatusCode(HttpStatus.OK.value());
//			return new ResponseEntity<ResponseStructure<List<Ticket>>>(structure, HttpStatus.OK);
//
//	}

}
