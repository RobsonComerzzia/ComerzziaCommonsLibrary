package com.seidor.comerzzia.commons.abstracts;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

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
			.uri(url)
			.body(body)
		    .headers(httpHeaders -> {
		         httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		         httpHeaders.setBearerAuth(token);
		    })
			.accept(MediaType.APPLICATION_JSON)
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
