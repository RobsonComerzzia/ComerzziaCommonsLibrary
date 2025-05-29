package com.seidor.comerzzia.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seidor.comerzzia.api.v1.assembler.UsuarioInputDisassembler;
import com.seidor.comerzzia.api.v1.assembler.UsuarioModelAssembler;
import com.seidor.comerzzia.api.v1.model.UsuarioModel;
import com.seidor.comerzzia.api.v1.model.input.SenhaInput;
import com.seidor.comerzzia.api.v1.model.input.UsuarioComSenhaInput;
import com.seidor.comerzzia.api.v1.model.input.UsuarioInput;
import com.seidor.comerzzia.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.seidor.comerzzia.core.security.ApiSecurity;
import com.seidor.comerzzia.core.security.CheckSecurity;
import com.seidor.comerzzia.domain.model.master.Usuario;
import com.seidor.comerzzia.domain.repository.master.UsuarioRepository;
import com.seidor.comerzzia.domain.service.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@Autowired
	private ApiSecurity apiSecurity;

	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public List<UsuarioModel> findAll() {
		List<Usuario> todasUsuarios = usuarioRepository.findAll();
		
		return usuarioModelAssembler.toCollectionModel(todasUsuarios);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping("/{usuarioId}")
	public UsuarioModel find(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.findOrFail(usuarioId);

		return usuarioModelAssembler.toModel(usuario);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel insert(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);

		String clientId = apiSecurity.getClientId();
		
		usuario.setClientId(clientId);

		usuario = usuarioService.save(usuario);
		
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
	@Override
	@PutMapping("/{usuarioId}")
	public UsuarioModel update(@PathVariable Long usuarioId,
			@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuarioAtual = usuarioService.findOrFail(usuarioId);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		usuarioAtual = usuarioService.save(usuarioAtual);
		
		return usuarioModelAssembler.toModel(usuarioAtual);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
	@Override
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> updatePassword(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
		usuarioService.updatePassword(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
		return ResponseEntity.noContent().build();
	}
	
}
