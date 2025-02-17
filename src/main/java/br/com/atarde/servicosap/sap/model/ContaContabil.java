package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class ContaContabil implements Serializable {

	// tabela OACT

	private String id;

	private String descricao;

	private Empresa empresa;

	private OrigemExtrato origemExtrato;

	private Banco banco;

	public ContaContabil() {

	}

	public ContaContabil(String id, Empresa empresa) {

		this.id = id;
		this.empresa = empresa;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public OrigemExtrato getOrigemExtrato() {
		return origemExtrato;
	}

	public void setOrigemExtrato(OrigemExtrato origemExtrato) {
		this.origemExtrato = origemExtrato;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
