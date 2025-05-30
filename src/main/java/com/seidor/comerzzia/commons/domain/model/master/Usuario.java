package com.seidor.comerzzia.commons.domain.model.master;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "client_id", nullable = false)
	private String clientId;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email; 
	
	@Column(nullable = false)
	private String senha;
	
	@CreationTimestamp
	@Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private Set<Grupo> grupos = new HashSet<>();
	
	public boolean removeGroup(Grupo grupo) {
		return getGrupos().remove(grupo);
	}
	
	public boolean addGroup(Grupo grupo) {
		return getGrupos().add(grupo);
	}
	
	@ManyToMany
	@JoinTable(name = "usuario_empresa", joinColumns = @JoinColumn(name = "usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "empresa_id"))
	private Set<Empresa> empresas = new HashSet<>();
	
	public boolean removeEmpresa(Empresa empresa) {
		return getEmpresas().remove(empresa);
	}
	
	public boolean addEmpresa(Empresa empresa) {
		return getEmpresas().add(empresa);
	}
	
	public boolean isNew() {
		return getId() == null;
	}
	
}
