package br.com.atarde.servicosap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosap.sap.model.DocumentoAB;
import br.com.atarde.servicosap.sap.model.Estoque;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosap.sap.model.Origem;

@SuppressWarnings("serial")
public class TransferenciaEstoque extends DocumentoAB implements Serializable {

	private Long interfaceId;
	private Date dataVencimento;
	private String observacao;
	private String observacaoDiario;
	private Estoque estoqueOrigem;
	private Estoque estoqueDestino;

	private NotaFiscalSaida notaFiscalSaidaReferenciada;

	private DevolucaoNotaFiscalSaida devolucaoNotaFiscalSaidaReferenciada;

	private Origem origem;

	private List<TransferenciaEstoqueLinha> linhas;

	public TransferenciaEstoque() {

	}

	public TransferenciaEstoque(Long interfaceId) {

		this.interfaceId = interfaceId;

	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacaoDiario() {
		return observacaoDiario;
	}

	public void setObservacaoDiario(String observacaoDiario) {
		this.observacaoDiario = observacaoDiario;
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

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public List<TransferenciaEstoqueLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<TransferenciaEstoqueLinha> linhas) {
		this.linhas = linhas;
	}

	public NotaFiscalSaida getNotaFiscalSaidaReferenciada() {
		return notaFiscalSaidaReferenciada;
	}

	public void setNotaFiscalSaidaReferenciada(NotaFiscalSaida notaFiscalSaidaReferenciada) {
		this.notaFiscalSaidaReferenciada = notaFiscalSaidaReferenciada;
	}

	public DevolucaoNotaFiscalSaida getDevolucaoNotaFiscalSaidaReferenciada() {
		return devolucaoNotaFiscalSaidaReferenciada;
	}

	public void setDevolucaoNotaFiscalSaidaReferenciada(DevolucaoNotaFiscalSaida devolucaoNotaFiscalSaidaReferenciada) {
		this.devolucaoNotaFiscalSaidaReferenciada = devolucaoNotaFiscalSaidaReferenciada;
	}

}
