package com.seidor.comerzzia.core.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

public @interface CheckSecurity {

	public @interface UsuariosGruposPermissoes {
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and "
				+ "@apiSecurity.isAuthenticateUserEquals(#usuarioId)")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarPropriaSenha { }
		
		@PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('ROLE_EDITAR_USUARIOS_GRUPOS_PERMISSOES') or "
				+ "@apiSecurity.isAuthenticateUserEquals(#usuarioId))")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeAlterarUsuario { }

		@PreAuthorize("@apiSecurity.canWriteUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeEditar { }
		

		@PreAuthorize("@apiSecurity.canReadUsuariosGruposPermissoes()")
		@Retention(RUNTIME)
		@Target(METHOD)
		public @interface PodeConsultar { }
		
	}
		
}
