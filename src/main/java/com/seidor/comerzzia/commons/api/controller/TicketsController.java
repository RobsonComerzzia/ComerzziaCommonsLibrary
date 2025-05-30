package com.seidor.comerzzia.commons.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seidor.comerzzia.commons.constants.Constants;
import com.seidor.comerzzia.commons.domain.service.GenerateXmlService;

@RestController
@RequestMapping(path = "/interno/v1/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketsController {
	
	@Autowired
	private GenerateXmlService service;

	@PostMapping()
	@ResponseStatus(HttpStatus.OK)
	public void generate() {
		
		service.generate(Constants.SIM);

	}
	
}
