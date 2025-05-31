package com.seidor.comerzzia.commons.domain.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seidor.comerzzia.commons.domain.model.master.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
