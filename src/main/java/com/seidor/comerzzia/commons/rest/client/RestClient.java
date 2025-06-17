package com.seidor.comerzzia.commons.rest.client;

public interface RestClient {
	
	public <T> T post(Class<?> body, String url) throws Exception;

}
