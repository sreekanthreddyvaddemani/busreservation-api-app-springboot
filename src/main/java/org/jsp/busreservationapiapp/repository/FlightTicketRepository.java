package org.jsp.busreservationapiapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.FlightTicket;
import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface FlightTicketRepository extends JpaRepository<FlightTicket, Integer> {

}
