package org.jsp.busreservationapiapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.busreservationapiapp.dto.ResponseStructure;
import org.jsp.busreservationapiapp.dto.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
