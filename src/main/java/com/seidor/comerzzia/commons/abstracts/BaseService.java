package com.seidor.comerzzia.commons.abstracts;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.commons.domain.repository.comerzzia.TicketsRepository;

@Service
public abstract class BaseService {

	protected final TicketsRepository ticketsRepository;
	
	public BaseService(TicketsRepository ticketsRepository) {
		
		this.ticketsRepository = ticketsRepository;
		
	}
	
	protected List<Ticket> extractFull(String procesado, BigInteger idTipoDocumento) {
		
		return ticketsRepository.findByProcesadoAndIdTipoDocumento(procesado, idTipoDocumento);
		
	}

	protected String getBlobToString(byte[] ticketBlob) {
	
		return new String(ticketBlob, StandardCharsets.UTF_8);
		
	}
	
	protected JSONObject getBlobToJson(byte[] ticketBlob) {
		
		return XML.toJSONObject(getBlobToString(ticketBlob));
		
	}

}
