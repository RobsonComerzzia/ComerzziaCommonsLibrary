package com.seidor.comerzzia.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.seidor.comerzzia.api.v1.model.UsuarioModel;
import com.seidor.comerzzia.api.v1.model.input.SenhaInput;
import com.seidor.comerzzia.api.v1.model.input.UsuarioComSenhaInput;
import com.seidor.comerzzia.api.v1.model.input.UsuarioInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Usuários")
public interface UsuarioControllerOpenApi {

	@Operation(summary = "Lista os usuários")
	List<UsuarioModel> findAll();

	@Operation(summary = "Busca um usuário por ID", responses = {
			@ApiResponse(responseCode = "200"),
			@ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) }),
	})
	UsuarioModel find(@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);

	@Operation(summary = "Cadastra um usuário", responses = {
			@ApiResponse(responseCode = "201", description = "Usuário cadastrado"),
	})
	UsuarioModel insert(
			@RequestBody(description = "Representação de um novo usuário", required = true) UsuarioComSenhaInput usuarioInput);

	@Operation(summary = "Atualiza um usuário por ID", responses = {
			@ApiResponse(responseCode = "200", description = "Usuário atualizado"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	UsuarioModel update(
			@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,
			@RequestBody(description = "Representação de um usuário com os novos dados", required = true) UsuarioInput usuarioInput);

	@Operation(summary = "Atualiza a senha de um usuário", responses = {
			@ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {
					@Content(schema = @Schema(ref = "Problema")) })
	})
	ResponseEntity<Void> updatePassword(
			@Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,
			@RequestBody(description = "Representação de uma nova senha", required = true) SenhaInput novaSenhaInput);

}