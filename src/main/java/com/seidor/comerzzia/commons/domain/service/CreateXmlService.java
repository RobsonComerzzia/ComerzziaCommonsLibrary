package com.seidor.comerzzia.commons.domain.service;

import java.math.BigInteger;

public interface CreateXmlService {
	
	/**
	 * Inicia o processamento
	 * 
	 * @param parametro Filtro do campo procesado: S - Sim, E ou X - Retorno de venda
	 * @param idTipoDocumento Filtro do tipo de documento
	 */
	void start(String parametro, BigInteger idTipoDocumento);

}
