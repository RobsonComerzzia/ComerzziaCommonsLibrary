package com.seidor.comerzzia.commons.abstracts;

import java.nio.charset.StandardCharsets;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.commons.api.v1.model.XmlModel;
import com.seidor.comerzzia.commons.constants.Constants;

import jakarta.persistence.MappedSuperclass;

@Service
@MappedSuperclass
public abstract class XmlBase extends XmlCreator {

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
		
		String xmlFiscalEncode = filterTagByString(contentXmlTicket, Constants.EXPRESSAO_XPATH_XML_FISCAL);
		
		if (xmlFiscalEncode != null && xmlFiscalEncode != "") {
			return this.getEncodeToString(xmlFiscalEncode);
		}
		
		return null;
		
	}

	protected String generateFileName(String xmlFiscal) {
		
		String fileName = filterTagByString(xmlFiscal, Constants.EXPRESSAO_XPATH_ID_NFE);
		
		fileName = fileName.replace("NFe", "");
		
		if (fileName != "" && fileName != null)
			fileName = fileName + "-ProcNFCe.xml";
		else
			fileName = "uid_ticket_" + filterTagByString(xmlFiscal, "//uid_ticket") + ".xml";
		
		return fileName;
	}
	
	protected void createXmlFile(String content, String path, String fileName) {
		
		createXml(content, path, fileName);
		
	}

}
