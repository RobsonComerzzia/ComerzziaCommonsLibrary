package com.seidor.comerzzia.commons.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Ticket.pk_ticket> {
	
	public List<Ticket> findByProcesado(String procesado);

}
