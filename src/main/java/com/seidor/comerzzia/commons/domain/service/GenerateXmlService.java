package com.seidor.comerzzia.commons.domain.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.domain.repository.comerzzia.TicketRepository;

@Service
public class GenerateXmlService extends BaseService {

	public GenerateXmlService(TicketRepository ticketRepository) {
		super(ticketRepository);
	}
	
	@Async
	public void generate(String parametro) {
		
		
		
	}
	

}
