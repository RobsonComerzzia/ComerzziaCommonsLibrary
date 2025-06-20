package com.seidor.comerzzia.commons.abstracts;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.seidor.comerzzia.commons.constants.Constants;

import jakarta.persistence.MappedSuperclass;
import lombok.extern.slf4j.Slf4j;

@Slf4j		
@Service
@MappedSuperclass
public abstract class RestClientB1<T> {
	
	protected void post(T body, String url, String apiKey, String token) throws Exception {
		
		RestClient restClient = RestClient.builder()
				.baseUrl(url)
				.build();
		
		try {
			restClient.post()
					.uri(uriBuilder -> uriBuilder
							.build())
						.body(body != null ? body : null)
						.header(Constants.API_KEY, apiKey)
						.header(Constants.TOKEN, token)
						.retrieve()
			        .onStatus(httpStatusCode -> httpStatusCode.is2xxSuccessful(), (req, res) -> {
			        	 String json = new String(res.getBody().readAllBytes());
			        	 log.info("{} - Sucesso {} : {}", res.getStatusCode(), json);
			         })
					.onStatus(httpStatusCode -> httpStatusCode.is4xxClientError(), (req, res) -> {
						 String json = new String(res.getBody().readAllBytes());
						 log.error("{} - Erro {} : {}", res.getStatusCode(), json);
					})
					.onStatus(httpStatusCode -> httpStatusCode.is5xxServerError(), (req, res) -> {
					     String json = new String(res.getBody().readAllBytes());
					     log.error("{} - ERRO {}: {}", res.getStatusCode(), json);
					})	
					.toBodilessEntity();		
		} catch (Exception e) {
			log.error("Erro: ", e.getLocalizedMessage());
		}
		
	}
	

}
