package com.seidor.comerzzia.commons.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
public class GrupoInput {

	@Schema(example = "Supervisor")
	@NotBlank
	private String nome;
	
}
