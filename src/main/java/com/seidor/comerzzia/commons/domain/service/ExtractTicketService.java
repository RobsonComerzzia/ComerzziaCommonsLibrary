package com.seidor.comerzzia.commons.domain.service;

import java.util.List;

import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;

public interface ExtractTicketService {

	List<Ticket> extractFull(String procesado);
	
	String getBlobToString(byte[] ticketBlob);
	
}
