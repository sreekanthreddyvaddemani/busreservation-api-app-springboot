package org.jsp.busreservationapiapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dao.AdminDao;
import org.jsp.busreservationapiapp.dao.TrainDao;
import org.jsp.busreservationapiapp.dto.Admin;
import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Train;
import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.jsp.busreservationapiapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrainService {
	
	@Autowired
	private TrainDao tDao;
	@Autowired
	private AdminDao adminDao;
	

	public ResponseEntity<ResponseStructure<Train>> saveTrain(Train train, int admin_id) {
		Optional<Admin> recAdmin = adminDao.findById(admin_id);
		ResponseStructure<Train> structure = new ResponseStructure<>();
		if (recAdmin.isPresent()) {
			Admin a = recAdmin.get();
			a.getTrains().add(train);
			train.setAdmin(a);
			adminDao.updateAdmin(a);
			structure.setData(tDao.saveTrain(train));
			structure.setMessage("Bus Added Successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Train>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Train>> findTrain(int id) {
		ResponseStructure<Train> structure = new ResponseStructure<>();
		Optional<Train> recAdmin = tDao.findTrain(id);
		if (recAdmin.isPresent()) {
			structure.setData(recAdmin.get());
			structure.setMessage("Train Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Train>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}


	public ResponseEntity<ResponseStructure<List<Train>>> filtertrain(String from, String to, LocalDate dop) {
		ResponseStructure<List<Train>> structure = new ResponseStructure<>();
		structure.setData(tDao.filterTrain(from, to, dop));
		structure.setMessage("List of Trains");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Train>>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Train>>> findAll() {
		ResponseStructure<List<Train>> structure = new ResponseStructure<>();
		structure.setData(tDao.findAll());
		structure.setMessage("List of Buses");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Train>>>(structure, HttpStatus.OK);
	}

//	public ResponseEntity<ResponseStructure<List<TrainTicket>>> findTrainBySeat(int id,String seattype) {
//		ResponseStructure<List<TrainTicket>> structure = new ResponseStructure<>();
//		structure.setData(tDao.findTrainBySeat(id,seattype));
//		structure.setMessage("List of Tickets");
//		structure.setStatusCode(HttpStatus.OK.value());
//		return new ResponseEntity<ResponseStructure<List<TrainTicket>>>(structure, HttpStatus.OK);
//	}

}
