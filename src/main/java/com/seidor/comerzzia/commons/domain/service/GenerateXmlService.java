package com.seidor.comerzzia.commons.domain.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.abstracts.BaseService;
import com.seidor.comerzzia.commons.api.v1.model.XmlModel;
import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.commons.domain.repository.comerzzia.TicketsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GenerateXmlService extends BaseService {
	
	public GenerateXmlService(TicketsRepository ticketsRepository, XmlCreator xmlCreator) {
		super(ticketsRepository, xmlCreator);
	}
	
	@Async
	public void generateXmlFiscal(String parametro, BigInteger idTipoDocumento) {
		
		List<Ticket> tickets = this.extractTicketsFull(parametro, idTipoDocumento);
	
		tickets.forEach(ticket -> {
			
			XmlModel content = this.getContentToFile(ticket.getTicket());
			
			String xmlFiscal = getXmlFiscal(content.getContentString());
			
			if (xmlFiscal != null && xmlFiscal != "") {
				String fileName = this.generateFileName(xmlFiscal);
				xmlCreator.createXml(xmlFiscal, "${XML_PATH}", fileName);
			} else {
				log.warn("[GenerateXmlService] - Não foi possível gerar o arquivo XML para o ticket {}", ticket.getIdTicket());
			}
					
		});
			
	}

}
