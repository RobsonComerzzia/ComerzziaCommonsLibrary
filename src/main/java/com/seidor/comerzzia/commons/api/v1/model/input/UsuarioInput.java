package com.seidor.comerzzia.commons.api.v1.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioInput {

	@Schema(example = "Roberto Silva")
	@NotBlank
	private String nome;

	@Schema(example = "roberto.silva@seidor.com")
	@NotBlank
	@Email
	private String email;
	
}
