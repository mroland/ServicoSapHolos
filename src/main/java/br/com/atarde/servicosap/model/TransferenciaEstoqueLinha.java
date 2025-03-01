package br.com.atarde.servicosap.model;

import java.io.Serializable;

import br.com.atarde.servicosap.sap.model.DocumentoLinhaAB;
import br.com.atarde.servicosap.sap.model.Estoque;

@SuppressWarnings("serial")
public class TransferenciaEstoqueLinha extends DocumentoLinhaAB implements Serializable {

	private Long interfaceId;
	private Estoque estoqueOrigem;
	private Estoque estoqueDestino;
	private TransferenciaEstoque transferenciaEstoque;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public Estoque getEstoqueOrigem() {
		return estoqueOrigem;
	}

	public void setEstoqueOrigem(Estoque estoqueOrigem) {
		this.estoqueOrigem = estoqueOrigem;
	}

	public Estoque getEstoqueDestino() {
		return estoqueDestino;
	}

	public void setEstoqueDestino(Estoque estoqueDestino) {
		this.estoqueDestino = estoqueDestino;
	}

	public TransferenciaEstoque getTransferenciaEstoque() {
		return transferenciaEstoque;
	}

	public void setTransferenciaEstoque(TransferenciaEstoque transferenciaEstoque) {
		this.transferenciaEstoque = transferenciaEstoque;
	}

}
