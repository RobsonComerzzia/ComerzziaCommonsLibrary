package com.seidor.comerzzia.commons.domain.exception;

public class EntityInUseException extends BussinessRoleException {

	private static final long serialVersionUID = 1L;

	public EntityInUseException(String mensagem) {
		super(mensagem);
	}
	
}
