package com.seidor.comerzzia.commons.constants;

import java.math.BigInteger;

public final class Constants {
	
	public static final String UTF8 = "UTF-8";
	
	public static final String API_KEY = "x-api-key";
	
	public static final String TOKEN = "token";
	
	public static final String PROTOCOL_HTTP = "http://";
	
	public static final String SIM = "S";
	
	public static final String NAO = "N";
	
	public static final String UNIDADE = "U";
	
	public static final String GENERAL = "GENERAL";
	
	public static final String GRAN_TYPE = "grant_type";
	
	public static final String SCOPE = "scope";
		
	public static final String PT_BR = "PT-BR";
	
	public static final String BR = "BR";
	
	public static final String CNPJ = "CNPJ";
	
	public static final String CPF = "CPF";
	
	public static final BigInteger ID_TIPO_DOCUMENTO_XML = new BigInteger("12001");
	
	public static final String EXPRESSAO_XPATH_XML_FISCAL = "//fiscal_data/property[name='XML']/value";
	
	public static final String EXPRESSAO_XPATH_AUTHORIZATION_CODE = "//fiscal_data/property[name='AUTHORIZATION_CODE']/value";
	
	public static final String EXPRESSAO_XPATH_NFE_KEY = "//fiscal_data/property[name='NFE_KEY']/value";
	
	public static final String EXPRESSAO_XPATH_SITEF = "//adicionales/SITEF_DATA/text()";
	
	public static final String EXPRESSAO_XPATH_PROPRIEDADE_ID= "//@Id";
	
	public static final String EXPRESSAO_XPATH_UID_TICKET = "//uid_ticket";
	
	public static final String X = "X";

}
