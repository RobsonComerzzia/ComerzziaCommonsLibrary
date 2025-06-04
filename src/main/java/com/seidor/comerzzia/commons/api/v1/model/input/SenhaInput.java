package com.seidor.comerzzia.commons.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

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
