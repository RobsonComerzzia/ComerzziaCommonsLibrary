package com.seidor.comerzzia.commons.domain.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seidor.comerzzia.commons.domain.model.master.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
