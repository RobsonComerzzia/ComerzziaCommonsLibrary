package com.seidor.comerzzia.commons;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.seidor.comerzzia.commons.core.io.Base64ProtocolResolver;

@SpringBootApplication
public class ComerzziaCommonsLibraryApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		SpringApplication app = new SpringApplication(ComerzziaCommonsLibraryApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}
