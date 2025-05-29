package com.seidor.comerzzia.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
public class SenhaInput {

	@Schema(example = "12345", type = "string")
	@NotBlank
	private String senhaAtual;

	@Schema(example = "12345", type = "string")
	@NotBlank
	private String novaSenha;
}
