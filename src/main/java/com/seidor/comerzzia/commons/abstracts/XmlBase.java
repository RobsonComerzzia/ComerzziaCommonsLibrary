package com.seidor.comerzzia.commons.abstracts;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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

	protected String generateFileName(String content) {
		
		String fileName = filterTagByString(content, Constants.EXPRESSAO_XPATH_ID_NFE);
		
		fileName = fileName.replace("NFe", "");
		
		if (fileName != "" && fileName != null)
			fileName = fileName + "-ProcNFCe.xml";
		else
			fileName = "uid_ticket_" + filterTagByString(content, Constants.EXPRESSAO_XPATH_UID_TICKET) + ".xml";
		
		return fileName;
	}
	
	protected void createXmlFile(String content, String path, String fileName) {
		
		createXml(content, path, fileName);
		
	}
	
	protected String filterTagByFile(File xmlFile, String expression) {
		
		DocumentBuilder dBuilder;
		
		String value = null;
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();
            
            XPathExpression xPathExpression = xpath.compile(expression);
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                value = element.getTextContent();
            }
			
		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return value;
	}

	protected String filterTagByString(String content, String expression) {
		
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xPath = xpf.newXPath();

        InputSource inputSource = new InputSource(new StringReader(content));
        String result = null;
		try {
			result = (String) xPath.evaluate(expression, inputSource, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
