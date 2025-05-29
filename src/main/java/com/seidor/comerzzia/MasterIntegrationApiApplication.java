package com.seidor.comerzzia;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.seidor.comerzzia.core.io.Base64ProtocolResolver;

@SpringBootApplication
public class MasterIntegrationApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		var app = new SpringApplication(MasterIntegrationApiApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}
