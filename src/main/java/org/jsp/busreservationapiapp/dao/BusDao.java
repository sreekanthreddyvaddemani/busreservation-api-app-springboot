package org.jsp.busreservationapiapp.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.jsp.busreservationapiapp.repository.BusRepository;
import org.jsp.busreservationapiapp.repository.TicketRepository;
import org.jsp.busreservationapiapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	@Autowired
	private BusRepository repository;
	
	@Autowired
	private UserRepository Urepository;
	
	@Autowired
	private TicketRepository Trepository;



	public Bus saveBus(Bus bus) {
		return repository.save(bus);
	}

	public Bus updateBus(Bus bus) {
		return repository.save(bus);
	}

	public List<Bus> filter(String from, String to, LocalDate dop) {
		    return repository.filter(from, to, dop);
		}
	

	public Optional<Bus> findBus(int id) {
		return repository.findById(id);
	}

	public List<Bus> findAll() {
		return repository.findAll();
	}



	public String deleteBus(Bus bus) {
		repository.delete(bus);
	return "BUS deleted";

}

	public Optional<Bus> findBusByTicket(long buscode) {
		return repository.findByTicket(buscode);
	}

	

	
	
	



//	public List<String> filterfrom() {
//		System.out.println("Executed Dao");
//		return repository.filterfrom();
//	}
}
