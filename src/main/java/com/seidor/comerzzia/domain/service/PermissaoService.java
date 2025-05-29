package com.seidor.comerzzia.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seidor.comerzzia.domain.exception.PermissionNotFoundException;
import com.seidor.comerzzia.domain.model.master.Permissao;
import com.seidor.comerzzia.domain.repository.master.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao findOrFail(Long permissaoId) {
		return permissaoRepository.findById(permissaoId)
			.orElseThrow(() -> new PermissionNotFoundException(permissaoId));
	}
	
}
