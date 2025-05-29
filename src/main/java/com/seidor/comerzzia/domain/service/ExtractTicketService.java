package com.seidor.comerzzia.domain.service;

import java.util.List;

import com.seidor.comerzzia.domain.model.comerzzia.Ticket;

public interface ExtractTicketService {

	List<Ticket> extractFull(String procesado);
	
	String getBlobToString(byte[] ticketBlob);
	
}
