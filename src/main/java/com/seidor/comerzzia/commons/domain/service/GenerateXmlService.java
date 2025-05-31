package com.seidor.comerzzia.commons.domain.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.abstracts.BaseService;
import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.commons.domain.repository.comerzzia.TicketsRepository;

@Service
public class GenerateXmlService extends BaseService {

	@Autowired
	private XmlCreator xmlCreator;
	
	public GenerateXmlService(TicketsRepository ticketsRepository) {
		super(ticketsRepository);
	}
	
	@Async
	public void generate(String parametro, BigInteger idTipoDocumento) {
		
		
		List<Ticket> tickets = this.extractFull(parametro, idTipoDocumento);
		
		tickets.forEach(ticket -> {
			
			String content = this.getBlobToString(ticket.getTicket());
			xmlCreator.createXml(content, "${XML_PATH}", "teste.xml");
					
		});
		
		
	}
	

}
