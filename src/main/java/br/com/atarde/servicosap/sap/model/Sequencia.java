/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class Sequencia implements Serializable {

	private Long id;
	private String descricao;
	private Empresa empresa;
	private SequenciaModelo sequenciaModelo;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public SequenciaModelo getSequenciaModelo() {
		return sequenciaModelo;
	}

	public void setSequenciaModelo(SequenciaModelo sequenciaModelo) {
		this.sequenciaModelo = sequenciaModelo;
	}

}
