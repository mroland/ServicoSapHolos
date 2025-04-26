package br.com.atarde.servicosap.model;

import br.com.atarde.servicosap.sap.model.DocumentoAB;
import br.com.atarde.servicosap.sap.model.Item;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaida;

@SuppressWarnings("serial")
public class TabelaUsuarioMovimentacao extends DocumentoAB {

	private Long interfaceId;
	private String tipoMovimentacao;
	private Item item;
	private Double quantidade;
	private String arquivoRemessa;

	private NotaFiscalSaida notaFiscalSaidaReferenciada;

	private DevolucaoNotaFiscalSaida devolucaoNotaFiscalSaidaReferenciada;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public String getArquivoRemessa() {
		return arquivoRemessa;
	}

	public void setArquivoRemessa(String arquivoRemessa) {
		this.arquivoRemessa = arquivoRemessa;
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
