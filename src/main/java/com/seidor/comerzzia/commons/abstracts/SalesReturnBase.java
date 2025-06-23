package com.seidor.comerzzia.commons.abstracts;

import java.sql.SQLIntegrityConstraintViolationException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class SalesReturnBase<T, ID> extends XmlBase {
	
	protected final JpaRepository<T, ID> repository;
	
    public SalesReturnBase(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }
	
	protected T markAsProcesado(T ticket) throws SQLIntegrityConstraintViolationException, ConstraintViolationException, DataIntegrityViolationException {
		
		return repository.save(ticket);
		
	}
	

}
