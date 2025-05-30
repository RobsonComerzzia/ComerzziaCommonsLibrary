package com.seidor.comerzzia.commons.abstracts;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.api.v1.model.XmlModel;
import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.commons.domain.repository.comerzzia.TicketsRepository;
import com.seidor.comerzzia.commons.domain.service.XmlCreator;

@Service
public abstract class BaseService {

	protected final TicketsRepository ticketsRepository;
	
	protected final XmlCreator xmlCreator;
	
	public BaseService(TicketsRepository ticketsRepository, XmlCreator xmlCreator) {
		
		this.ticketsRepository = ticketsRepository;
		this.xmlCreator = xmlCreator;
		
	}
	
	protected List<Ticket> extractTicketsFull(String procesado, BigInteger idTipoDocumento) {
		
		return ticketsRepository.findByProcesadoAndIdTipoDocumento(procesado, idTipoDocumento);
		
	}

	protected String getBlobToString(byte[] ticketBlob) {
	
		return new String(ticketBlob, StandardCharsets.UTF_8);
		
	}
	
	protected String getEncodeToString(String nfeEncode) {
		
		@SuppressWarnings("deprecation")
		byte[] nfeByte = Base64.decodeBase64(nfeEncode);
		
		return this.getBlobToString(nfeByte);
		
	}
	
	protected JSONObject getBlobToJson(byte[] ticketBlob) {
		
		return XML.toJSONObject(getBlobToString(ticketBlob));
		
	}
	
	protected XmlModel getContentToFile(byte[] ticketBlob) {
		
		XmlModel model = XmlModel.builder()
				.contentString(this.getBlobToString(ticketBlob))
				.jsonObect(this.getBlobToJson(ticketBlob))
				.build();
		
		return model;
		
	}
	
	protected String getXmlFiscal(String contentXmlTicket) {
		
		String xmlFiscalEncode = xmlCreator.filterTagByString(contentXmlTicket, "//fiscal_data/property[name='XML']/value");
		
		if (xmlFiscalEncode != null && xmlFiscalEncode != "") {
			return this.getEncodeToString(xmlFiscalEncode);
		}
		
		return null;
		
	}

	protected String generateFileName(String xmlFiscal) {
		
		String fileName = xmlCreator.filterTagByString(xmlFiscal, "//@Id");
		
		if (fileName != "" && fileName != null)
			fileName = fileName + "-ProcNFCe.xml";
		else
			fileName = null;
		
		return fileName;
	}

}
