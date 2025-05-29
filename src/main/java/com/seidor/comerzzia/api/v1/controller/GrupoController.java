package com.seidor.comerzzia.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seidor.comerzzia.api.v1.assembler.GrupoInputDisassembler;
import com.seidor.comerzzia.api.v1.assembler.GrupoModelAssembler;
import com.seidor.comerzzia.api.v1.model.GrupoModel;
import com.seidor.comerzzia.api.v1.model.input.GrupoInput;
import com.seidor.comerzzia.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.seidor.comerzzia.core.security.ApiSecurity;
import com.seidor.comerzzia.core.security.CheckSecurity;
import com.seidor.comerzzia.domain.model.master.Grupo;
import com.seidor.comerzzia.domain.repository.master.GrupoRepository;
import com.seidor.comerzzia.domain.service.GrupoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@Autowired
	private ApiSecurity apiSecurity;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping
	public List<GrupoModel> findAll() {
		List<Grupo> todosGrupos = grupoRepository.findAll();
		
		return grupoModelAssembler.toCollectionModel(todosGrupos);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@Override
	@GetMapping("/{grupoId}")
	public GrupoModel find(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.findOrFail(grupoId);
		
		return grupoModelAssembler.toModel(grupo);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel insert(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		
		String clientId = apiSecurity.getClientId();
		
		grupo.setClientId(clientId);
		
		grupo = grupoService.save(grupo);
		
		return grupoModelAssembler.toModel(grupo);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@PutMapping("/{grupoId}")
	public GrupoModel update(@PathVariable Long grupoId,
			@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = grupoService.findOrFail(grupoId);
		
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		
		grupoAtual = grupoService.save(grupoAtual);
		
		return grupoModelAssembler.toModel(grupoAtual);
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@Override
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable Long grupoId) {
		grupoService.delete(grupoId);
		return ResponseEntity.noContent().build();
	}
	
}
