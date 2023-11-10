package org.jsp.busreservationapiapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dao.TrainDao;
import org.jsp.busreservationapiapp.dao.TrainTicketDao;
import org.jsp.busreservationapiapp.dao.UserDao;
import org.jsp.busreservationapiapp.dto.EmailConfiguration;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Train;
import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.jsp.busreservationapiapp.dto.User;
import org.jsp.busreservationapiapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class TrainTicketService {

		@Autowired
		private TrainDao trainDao;
		@Autowired
		private UserDao userDao;
		@Autowired
		private TrainTicketDao trainticketDao;

		@Autowired
		private ReservationApiEmailService service;

		@Autowired
		private EmailConfiguration configuration;

		public ResponseEntity<ResponseStructure<TrainTicket>> saveTrainTicket(TrainTicket ticket, int user_id, int train_id) {
			Optional<User> recUser = userDao.findbyid(user_id);
			Optional<Train> recTrain = trainDao.findTrain(train_id);
			ResponseStructure<TrainTicket> structure = new ResponseStructure<>();
			if (recUser.isPresent() && recTrain.isPresent()) {
				User u = recUser.get();
				Train t = recTrain.get();
//				ticket.setCost(ticket.getNumber_of_seats() * b.getCost_per_seat());
				u.getTraintickets().add(ticket);
				t.getTraintickets().add(ticket);
				ticket.setUser(u);
				ticket.setTrain(t);
				configuration.setTo(u.getEmail());
				configuration.setSubject("Ticket for "+t.getFrom().toUpperCase()+" - "+t.getTo().toUpperCase()
						);
				configuration.setText("Happy Journey");
				String message = service.sendEmail(configuration);
				structure.setData(trainticketDao.saveTicket(ticket));
				trainDao.updateTrain(t);
				userDao.updateUser(u);
				structure.setMessage(message);
				structure.setStatusCode(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<TrainTicket>>(structure, HttpStatus.CREATED);
			}
			throw new IdNotFoundException();
		}

		public ResponseEntity<ResponseStructure<List<TrainTicket>>> findTrainTicketsBySeat(String seattype) {
			ResponseStructure<List<TrainTicket>> structure = new ResponseStructure<>();
		       structure.setData(trainticketDao.findTrainTicketsBySeat(seattype));
		       structure.setMessage("List of Tickets");
		       structure.setStatusCode(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<List<TrainTicket>>>(structure, HttpStatus.OK);
		}
	
	}

