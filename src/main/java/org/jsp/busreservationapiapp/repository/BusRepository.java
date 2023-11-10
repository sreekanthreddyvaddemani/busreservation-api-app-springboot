package org.jsp.busreservationapiapp.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusRepository extends JpaRepository<Bus, Integer> {

	@Query("select b from Bus b where b.from=?1 and b.to=?2 and b.dop=?3")
	public List<Bus> filter(String from, String to, LocalDate dop);

	
	@Query("select b from Bus b where b.buscode=?1")
	public Optional<Bus> findByTicket(long buscode);

	
	
	
	
	
//	@Query("select b.from from Bus b where b.from like='k%'")
//	public List<String> filterfrom();

	

}
