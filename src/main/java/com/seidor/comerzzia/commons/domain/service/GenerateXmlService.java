package com.seidor.comerzzia.commons.domain.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.abstracts.XmlBaseService;
import com.seidor.comerzzia.commons.api.v1.model.XmlModel;
import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.commons.domain.repository.comerzzia.TicketsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GenerateXmlService extends XmlBaseService {
	
	@Value("${commons.path.xml.local}")
	private String pathXmlLocal;
	
	public GenerateXmlService(TicketsRepository ticketsRepository, XmlCreator xmlCreator) {
		super(ticketsRepository, xmlCreator);
	}
	
	@Async
	public void generateXmlFiscal(String parametro, BigInteger idTipoDocumento) {
		
		List<Ticket> tickets = this.extractTicketsFull(parametro, idTipoDocumento);
	
		tickets.forEach(ticket -> {
			
			XmlModel content = getContentToFile(ticket.getTicket());
			
			String xmlFiscal = getXmlFiscal(content.getContentString());
			String fileName = null;
			
			if (xmlFiscal != null && xmlFiscal != "") {
				fileName = this.generateFileName(xmlFiscal);
				createXml(xmlFiscal, pathXmlLocal, fileName);
			} else {
				fileName = this.generateFileName(content.getContentString());
				createXml(content.getContentString(), pathXmlLocal, fileName);
				//log.warn("[GenerateXmlService] - Não foi possível gerar o arquivo XML Fiscal para o ticket id {}", ticket.getIdTicket());
			}
					
		});
			
	}
	
	@Async
	public void generateXmlFull(String parametro, BigInteger idTipoDocumento) {
		
		List<Ticket> tickets = this.extractTicketsFull(parametro, idTipoDocumento);
	
		tickets.forEach(ticket -> {
			
			XmlModel content = getContentToFile(ticket.getTicket());
			
			if (content.getContentString() != null && content.getContentString() != "") {
				String fileName = this.generateFileName(content.getContentString());
				createXml(content.getContentString(), pathXmlLocal, fileName);
			} else {
				log.warn("[GenerateXmlService] - Não foi possível gerar o arquivo XML Full para o ticket id {}", ticket.getIdTicket());
			}
					
		});
			
	}

}
