package com.seidor.comerzzia.domain.service;

import java.util.Base64;
import java.util.List;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.domain.repository.comerzzia.TicketRepository;

@Service
public class ExtractTicketServiceImpl implements ExtractTicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public List<Ticket> extractFull(String procesado) {
		
		List<Ticket> tickets = ticketRepository.findByProcesado(procesado);
		
		return tickets;
	}

	@Override
	public String getBlobToString(byte[] ticketBlob) {
	
		String xml = Base64.getEncoder().encodeToString(ticketBlob);
		
		JSONObject jsonObject = XML.toJSONObject(xml);
		
		return xml;
	}

}
