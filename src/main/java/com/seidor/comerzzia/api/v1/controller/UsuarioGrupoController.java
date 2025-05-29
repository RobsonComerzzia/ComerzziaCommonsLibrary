package com.seidor.comerzzia.api.v1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seidor.comerzzia.api.v1.assembler.GrupoModelAssembler;
import com.seidor.comerzzia.api.v1.model.GrupoModel;
import com.seidor.comerzzia.api.v1.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.seidor.comerzzia.core.security.CheckSecurity;
import com.seidor.comerzzia.domain.model.master.Usuario;
import com.seidor.comerzzia.domain.service.UsuarioService;


@RestController
@RequestMapping(path = "/v1/users/{usuarioId}/groups", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public List<GrupoModel> findAll(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.findOrFail(usuarioId);
		
		return grupoModelAssembler.toCollectionModel(usuario.getGrupos().stream().collect(Collectors.toList()));
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.disassociateGroup(usuarioId, grupoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.associateGroup(usuarioId, grupoId);
		
		return ResponseEntity.noContent().build();
	}

}
