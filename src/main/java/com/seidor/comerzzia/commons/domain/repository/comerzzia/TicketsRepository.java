package com.seidor.comerzzia.commons.domain.repository.comerzzia;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, Ticket.pk_ticket> {

	List<Ticket> findByProcesadoAndIdTipoDocumento(String parametro, BigInteger idTipoDocumento);
	
}
