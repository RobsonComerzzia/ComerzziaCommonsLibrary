package com.seidor.comerzzia.api.v1.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.seidor.comerzzia.api.v1.assembler.PermissaoModelAssembler;
import com.seidor.comerzzia.api.v1.model.PermissaoModel;
import com.seidor.comerzzia.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.seidor.comerzzia.core.security.CheckSecurity;
import com.seidor.comerzzia.domain.model.master.Grupo;
import com.seidor.comerzzia.domain.service.GrupoService;

@RestController
@RequestMapping(path = "/v1/groups/{grupoId}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public List<PermissaoModel> findAll(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.findOrFail(grupoId);
		
		return permissaoModelAssembler.toCollectionModel(grupo.getPermissoes().stream().collect(Collectors.toList()));
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> disassociate(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.disassociatePermission(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associate(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.associatePermission(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}

}
