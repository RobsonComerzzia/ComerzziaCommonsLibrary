package com.seidor.comerzzia.commons.domain.service;

public interface ProcessService {
	
	/**
	 * Inicia o processamento
	 * 
	 * @param parametro Filtro do campo procesado: S - Sim, E ou X - Retorno de venda
	 */
	void start(String parametro);

}
