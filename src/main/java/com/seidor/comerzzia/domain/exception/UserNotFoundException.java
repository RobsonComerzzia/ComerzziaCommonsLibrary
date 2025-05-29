package com.seidor.comerzzia.domain.exception;

public class UserNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public UserNotFoundException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de usuário com código %d", cozinhaId));
	}
	
}
