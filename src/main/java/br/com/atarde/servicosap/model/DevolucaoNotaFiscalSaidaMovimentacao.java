package br.com.atarde.servicosap.model;

@SuppressWarnings("serial")
public class DevolucaoNotaFiscalSaidaMovimentacao extends TabelaUsuarioMovimentacao {

	private DevolucaoNotaFiscalSaida devolucaoNotaFiscalSaida;

	public DevolucaoNotaFiscalSaida getDevolucaoNotaFiscalSaida() {
		return devolucaoNotaFiscalSaida;
	}

	public void setDevolucaoNotaFiscalSaida(DevolucaoNotaFiscalSaida devolucaoNotaFiscalSaida) {
		this.devolucaoNotaFiscalSaida = devolucaoNotaFiscalSaida;
	}

}
