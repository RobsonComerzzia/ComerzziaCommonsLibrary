package com.seidor.comerzzia.commons.abstracts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class XmlCreator {

	protected void createXml(String xmlContent, String path, String fileName)  {
		
		String directoryPath = (path != null ? path : System.getProperty("user.home")) + (fileName.contains("CFe") ? "\\XML\\Nao_enviado" : ((fileName.contains("uid_ticket_") ? "\\XML\\Nao_enviado" : "\\XML")));
        Path filePath = Paths.get(directoryPath, fileName);
        
        try {
        	
            Files.createDirectories(filePath.getParent());

            try (FileWriter fileWriter = new FileWriter(filePath.toFile())) {
                fileWriter.write(xmlContent);
            }
        } catch (IOException e) {
            log.error("Error creating XML file: " + e.getMessage());
            e.printStackTrace();
        }
		
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
}
