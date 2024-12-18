package br.com.atarde.servicosap.sap.model;

@SuppressWarnings("serial")
public class NotaFiscalSaidaLinha extends NotaFiscalSaidaLinhaAB{
	
	private NotaFiscalSaida notaFiscalSaida;
	
	public NotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}
	public void setNotaFiscalSaida(NotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}
	

}
