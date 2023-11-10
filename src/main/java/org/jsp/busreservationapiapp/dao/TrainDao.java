package org.jsp.busreservationapiapp.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Train;
import org.jsp.busreservationapiapp.dto.TrainTicket;
import org.jsp.busreservationapiapp.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
@Repository
public class TrainDao {

	@Autowired
	private TrainRepository repository;
	
	public Train saveTrain(Train train) {
		return repository.save(train);
	}

	
	public List<Train> filterTrain(String from,String to,LocalDate dop){
		return repository.findTrains(from,to,dop);
	}
	public List<Train> findAll() {
		return repository.findAll();
	}

	public Optional<Train> findTrain(int train_id) {
		return repository.findById(train_id);
	}


	public void updateTrain(Train t) {
         repository.save(t);
	}

//
//	public List<TrainTicket> findTrainBySeat(int id, String seattype) {
//	return repository.findTrainBySeat(id, seattype);
//	}


//	public List<TrainTicket> findTrainBySeat(int id, String seattype) {
//		return repository.findTrainBySeat(id, seattype);
//	}
	
}
