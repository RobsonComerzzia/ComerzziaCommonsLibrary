package com.seidor.comerzzia.commons.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seidor.comerzzia.commons.domain.exception.BussinessRoleException;
import com.seidor.comerzzia.commons.domain.exception.UserNotFoundException;
import com.seidor.comerzzia.commons.domain.model.master.Grupo;
import com.seidor.comerzzia.commons.domain.model.master.Usuario;
import com.seidor.comerzzia.commons.domain.repository.master.UsuarioRepository;

import jakarta.persistence.EntityManager;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	@Qualifier("primaryEntityManagerFactory")
	private EntityManager manager;

	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Transactional
	public Usuario save(Usuario usuario) {
		manager.detach(usuario);
		
		Optional<Usuario> existingUser = usuarioRepository.findByEmail(usuario.getEmail());
		
		if (existingUser.isPresent() && !existingUser.get().equals(usuario)) {
			throw new BussinessRoleException(
					String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}
		
		if (usuario.isNew()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		}
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void updatePassword(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = findOrFail(usuarioId);
		
		if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
			throw new BussinessRoleException("Senha atual informada não coincide com a senha do usuário.");
		}
		
		usuario.setSenha(passwordEncoder.encode(novaSenha));
	}

	@Transactional
	public void disassociateGroup(Long usuarioId, Long grupoId) {
		Usuario usuario = findOrFail(usuarioId);
		Grupo grupo = grupoService.findOrFail(grupoId);
		
		usuario.removeGroup(grupo);
	}
	
	@Transactional
	public void associateGroup(Long usuarioId, Long grupoId) {
		Usuario usuario = findOrFail(usuarioId);
		Grupo grupo = grupoService.findOrFail(grupoId);
		
		usuario.addGroup(grupo);
	}
	
	public Usuario findOrFail(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
			.orElseThrow(() -> new UserNotFoundException(usuarioId));
	}
	
}
