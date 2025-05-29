package com.seidor.comerzzia.commons.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seidor.comerzzia.commons.domain.exception.EntityInUseException;
import com.seidor.comerzzia.commons.domain.exception.GroupNotFountException;
import com.seidor.comerzzia.commons.domain.model.master.Grupo;
import com.seidor.comerzzia.commons.domain.model.master.Permissao;
import com.seidor.comerzzia.commons.domain.repository.master.GrupoRepository;

@Service
public class GrupoService {

	private static final String MSG_GRUPO_EM_USO 
		= "Grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PermissaoService permissaoService;
	
	@Transactional
	public Grupo save(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void delete(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new GroupNotFountException(grupoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}

	@Transactional
	public void disassociatePermission(Long grupoId, Long permissaoId) {
		Grupo grupo = findOrFail(grupoId);
		Permissao permissao = permissaoService.findOrFail(permissaoId);
		
		grupo.removePermission(permissao);
	}
	
	@Transactional
	public void associatePermission(Long grupoId, Long permissaoId) {
		Grupo grupo = findOrFail(grupoId);
		Permissao permissao = permissaoService.findOrFail(permissaoId);
		
		grupo.addPermission(permissao);
	}
	
	public Grupo findOrFail(Long grupoId) {
		return grupoRepository.findById(grupoId)
			.orElseThrow(() -> new GroupNotFountException(grupoId));
	}
	
}
