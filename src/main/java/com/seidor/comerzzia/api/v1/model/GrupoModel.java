package com.seidor.comerzzia.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GrupoModel  {
	@Schema(example = "1")
	private Long id;
	
	@Schema(example = "client-backend")
	private String clientId;

	@Schema(example = "Administrador")
	private String nome;
}
