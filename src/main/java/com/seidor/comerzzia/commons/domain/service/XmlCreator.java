package com.seidor.comerzzia.commons.domain.service;

import java.io.File;

public interface XmlCreator {

	void createXml(String content, String path, String fileName);
	
	String filterTagByFile(File file, String expression);
	
	String filterTagByString(String content, String expression);
	
}
