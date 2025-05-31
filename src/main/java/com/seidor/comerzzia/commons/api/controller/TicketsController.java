package com.seidor.comerzzia.commons.api.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seidor.comerzzia.commons.api.controller.openapi.TicketsControllerOpenApi;
import com.seidor.comerzzia.commons.api.v1.model.CommonsModel;
import com.seidor.comerzzia.commons.constants.Constants;
import com.seidor.comerzzia.commons.domain.service.GenerateXmlService;

@RestController
@RequestMapping(path = "/v1/commons/extract/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketsController implements TicketsControllerOpenApi<CommonsModel> {
	
	@Autowired
	private GenerateXmlService service;
	
	//@CheckSecurity.AllCliendIdPermissioes.CanReadWriteAndIsAuthenticated
	@PostMapping()
	@Override
	@ResponseStatus(HttpStatus.OK)
	public CommonsModel insert() {
	
		CommonsModel response = CommonsModel.builder()
				.protocol(UUID.randomUUID().toString())
				.dateTimeRequest(LocalDateTime.now())
				.build();
		
		service.generate(Constants.SIM, Constants.ID_TIPO_DOCUMENTO_XML);
		
		return response;

	}
	
}
