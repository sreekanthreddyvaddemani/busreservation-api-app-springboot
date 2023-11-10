package org.jsp.busreservationapiapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.Admin;
import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.Flight;
import org.jsp.busreservationapiapp.dto.Train;
import org.jsp.busreservationapiapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AdminDao {

	@Autowired
	private AdminRepository repository;
	
	public Admin saveAdmin(Admin admin) {
		return repository.save(admin);

	}

	public Admin updateAdmin(Admin admin) {
		return repository.save(admin);
	}

	public Optional<Admin> findbyid(int id) {
		return repository.findById(id);
	}

	public Optional<Admin> verifyAdmin(long phone, String password) {
		return repository.verifyAdmin(phone, password);
	}

	public Optional<Admin> findById(int admin_id) {
		return repository.findById(admin_id);
	}
	
	public List<Bus> adminBus(int admin_id){
		return repository.adminBus(admin_id);
	}
	public List<Train> adminTrain(int admin_id){
		return repository.adminTrain(admin_id);
	}

	public List<Flight> adminFlight(int admin_id) {
		return repository.adminFlight(admin_id);
	}

}
