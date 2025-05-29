package com.seidor.comerzzia.domain.exception;

public class GroupNotFountException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public GroupNotFountException(String mensagem) {
		super(mensagem);
	}
	
	public GroupNotFountException(Long estadoId) {
		this(String.format("Não existe um cadastro de grupo com código %d", estadoId));
	}
	
}
