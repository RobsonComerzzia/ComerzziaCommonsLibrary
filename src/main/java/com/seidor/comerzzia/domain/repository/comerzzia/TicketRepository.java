package com.seidor.comerzzia.domain.repository.comerzzia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seidor.comerzzia.domain.model.comerzzia.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Ticket.pk_ticket> {
	
	List<Ticket> findByProcesado(String procesado);

}
