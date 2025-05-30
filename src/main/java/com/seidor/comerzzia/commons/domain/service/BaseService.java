package com.seidor.comerzzia.commons.domain.service;

import java.util.Base64;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.commons.repository.TicketRepository;

@Service
public abstract class BaseService {

	protected static TicketRepository ticketRepository;
	
	public List<Ticket> extractFull(String procesado) {
		
		List<Ticket> tickets = ticketRepository.findByProcesado(procesado);
		
		return tickets;
	}

	public String getBlobToString(byte[] ticketBlob) {
	
		String xml = Base64.getEncoder().encodeToString(ticketBlob);
		
		JSONObject jsonObject = XML.toJSONObject(xml);
		
		jsonObject.getJSONObject("");
		
		return xml;
	}

}
