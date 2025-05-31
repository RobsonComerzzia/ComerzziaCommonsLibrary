package com.seidor.comerzzia.commons.domain.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.abstracts.BaseService;
import com.seidor.comerzzia.commons.api.v1.model.XmlModel;
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
	public void generateXml(String parametro, BigInteger idTipoDocumento) {
		
		
		List<Ticket> tickets = this.extractTicketsFull(parametro, idTipoDocumento);
	
		tickets.forEach(ticket -> {
			
			XmlModel content = this.getContentToFile(ticket.getTicket());
			
			String xmlFiscal = getXmlFiscal(content.getContentString());
			
			if (xmlFiscal != null && xmlFiscal != "") {
				
				String fileName = generateFileName(xmlFiscal);
				
				xmlCreator.createXml(xmlFiscal, "${XML_PATH}", fileName);
			}
					
		});
		
		
	}
	
	private String getXmlFiscal(String contentXmlTicket) {
		
		String xmlFiscalEncode = xmlCreator.filterTagByString(contentXmlTicket, "//fiscal_data/property[name='XML']/value");
		
		String xmlFiscal = null;
		
		if (xmlFiscalEncode != null && xmlFiscalEncode != "") {
			xmlFiscal = this.getEncodeToString(xmlFiscalEncode);
		}
		
		return xmlFiscal;
		
	}

	private String generateFileName(String xmlFiscal) {
		
		String fileName = xmlCreator.filterTagByString(xmlFiscal, "//infNFe/@Id");
		
		fileName = fileName + ".xml";
		
		return fileName;
	}

}
