package com.seidor.comerzzia.domain.repository.master;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seidor.comerzzia.domain.model.master.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	
}
