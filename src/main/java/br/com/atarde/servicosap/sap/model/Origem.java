package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class Origem implements Serializable {

	private Long id;

	private String descricao;
	
	private Empresa empresa;

	public Origem(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public Origem(){
		
	}

	public Origem(long id) {
		this.id = id;
	}

	public Origem(Empresa empresa, Long id) {
		
		this.empresa = empresa;
		
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	

}
