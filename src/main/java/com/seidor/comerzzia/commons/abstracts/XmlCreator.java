package com.seidor.comerzzia.commons.abstracts;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class XmlCreator {
	
    private static final String CFE_PREFIX = "CFe";
    
    private static final String UID_TICKET_PREFIX = "uid_ticket_";
    
    private static final String PATH_TICKET_PREFIX = "\\XML\\Ticket";
    
    private static final String PATH_FISCAL_PREFIX = "\\XML\\Fiscal";
    
    private static final String PATH_STANDARD_PREFIX = "\\XML\\Standard";
    
    private static final String USER_HOME_PREFIX = "user.home";

	protected String createXml(String xmlContent, String path, String fileName)  {
		
		String directoryPath = null;
		
		if (fileName != null && !fileName.isBlank()) {
			directoryPath = (path != null ? path : System.getProperty(USER_HOME_PREFIX)) + (fileName.contains(CFE_PREFIX) ? PATH_STANDARD_PREFIX : ((fileName.contains(UID_TICKET_PREFIX) ? PATH_TICKET_PREFIX : PATH_FISCAL_PREFIX)));
	        
			Path filePath = Paths.get(directoryPath, fileName);
	        
	        try {
	        	
	            Files.createDirectories(filePath.getParent());

	            try (FileWriter fileWriter = new FileWriter(filePath.toFile())) {
	                fileWriter.write(xmlContent);
	            }
	        } catch (IOException e) {
	            log.error("[XmlCreator] - Error creating XML file: " + e.getMessage());
	            e.printStackTrace();
	        }	
		}
		
		return directoryPath;
	}
	
}
