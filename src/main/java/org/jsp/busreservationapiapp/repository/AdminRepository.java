package org.jsp.busreservationapiapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.Admin;
import org.jsp.busreservationapiapp.dto.Bus;
import org.jsp.busreservationapiapp.dto.Flight;
import org.jsp.busreservationapiapp.dto.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	@Query("select a from Admin a where a.phone=?1 and a.password=?2")
	public Optional<Admin> verifyAdmin(long phone, String password);
	
	
	@Query("select a.buses from Admin a where a.id=?1")
	public List<Bus> adminBus(int admin_id);
	
	@Query("select a.trains from Admin a where a.id=?1")
	public List<Train> adminTrain(int admin_id);

	@Query("select a.flights from Admin a where a.id=?1")
	public List<Flight> adminFlight(int admin_id);
}
