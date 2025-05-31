package com.seidor.comerzzia.commons.abstracts;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64.Decoder;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.codehaus.stax2.ri.typed.StringBase64Decoder;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.api.v1.model.XmlModel;
import com.seidor.comerzzia.commons.domain.model.comerzzia.Ticket;
import com.seidor.comerzzia.commons.domain.repository.comerzzia.TicketsRepository;

@Service
public abstract class BaseService {

	protected final TicketsRepository ticketsRepository;
	
	public BaseService(TicketsRepository ticketsRepository) {
		
		this.ticketsRepository = ticketsRepository;
		
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

}
