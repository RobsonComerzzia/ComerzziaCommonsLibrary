package com.seidor.comerzzia.commons.domain.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seidor.comerzzia.commons.domain.model.master.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
