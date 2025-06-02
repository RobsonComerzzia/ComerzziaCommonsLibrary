package com.seidor.comerzzia.commons.abstracts;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class XmlCreator {

	protected void createXml(String xmlContent, String path, String fileName)  {
		
		String directoryPath = (path != null ? path : System.getProperty("user.home")) + (fileName.contains("CFe") ? "\\XML\\Standard" : ((fileName.contains("uid_ticket_") ? "\\XML\\Ticket" : "\\XML\\Fiscal")));
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
	
}
