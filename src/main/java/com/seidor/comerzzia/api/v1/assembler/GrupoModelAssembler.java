package com.seidor.comerzzia.api.v1.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.seidor.comerzzia.api.v1.model.GrupoModel;
import com.seidor.comerzzia.domain.model.master.Grupo;

@Component
public class GrupoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public GrupoModel toModel(Grupo grupo) {
		return modelMapper.map(grupo, GrupoModel.class);
	}
	
	public List<GrupoModel> toCollectionModel(List<Grupo> grupos) {
		return grupos.stream()
				.map(grupo -> toModel(grupo))
				.collect(Collectors.toList());
		
	}
	
}
