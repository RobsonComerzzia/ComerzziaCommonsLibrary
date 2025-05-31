package com.seidor.comerzzia.commons.domain.service;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.commons.domain.repository.TicketRepository;

@Service
public class GenerateXmlService extends BaseService {
	
	private TicketRepository ticketRepository;
	
	@Async
	public void generate(String parametro) {
		
		BaseService.ticketRepository = ticketRepository;
		
		List<Ticket> tickets = this.extractFull(parametro);
		
		
		
	}
	

}
