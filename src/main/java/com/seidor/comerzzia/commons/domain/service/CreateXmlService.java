package com.seidor.comerzzia.commons.domain.service;

import java.math.BigInteger;

public interface CreateXmlService {
	
	void create(String parametro, BigInteger idTipoDocumento);
	
	void sendToBucket(String parametro, BigInteger idTipoDocumento);

}
