package com.seidor.comerzzia.commons.domain.service;

import java.util.Base64;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.commons.domain.repository.comerzzia.TicketRepository;

@Component
public abstract class BaseService {

	@Autowired
	private static TicketRepository ticketRepository;
	
	public BaseService(TicketRepository ticketRepository) {
		BaseService.ticketRepository = ticketRepository;
	}
	
	public static List<Ticket> extractFull(String procesado) {
		
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
