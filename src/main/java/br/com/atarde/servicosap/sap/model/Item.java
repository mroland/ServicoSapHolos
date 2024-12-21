package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public class Item implements Serializable {

	// Tabela OITM

	private String id;
	private String descricao;
	private Origem origem;
	private String detalhe;
	private Empresa empresa;
	private Estoque estoque;
	private List<Estoque> estoques;
	private GrupoItem grupo;
	private TipoItem tipo;

	private Boolean flagControleEstoque;
	private Boolean flagItemVenda;
	private Boolean flagItemCompra;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public GrupoItem getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoItem grupo) {
		this.grupo = grupo;
	}

	public TipoItem getTipo() {
		return tipo;
	}

	public void setTipo(TipoItem tipo) {
		this.tipo = tipo;
	}

	public Boolean getFlagControleEstoque() {
		return flagControleEstoque;
	}

	public void setFlagControleEstoque(Boolean flagControleEstoque) {
		this.flagControleEstoque = flagControleEstoque;
	}

	public Boolean getFlagItemVenda() {
		return flagItemVenda;
	}

	public void setFlagItemVenda(Boolean flagItemVenda) {
		this.flagItemVenda = flagItemVenda;
	}

	public Boolean getFlagItemCompra() {
		return flagItemCompra;
	}

	public void setFlagItemCompra(Boolean flagItemCompra) {
		this.flagItemCompra = flagItemCompra;
	}

}
