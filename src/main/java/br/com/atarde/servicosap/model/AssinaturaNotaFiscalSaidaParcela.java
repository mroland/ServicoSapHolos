package br.com.atarde.servicosap.model;

import br.com.atarde.servicosap.sap.model.ParcelaAB;

@SuppressWarnings("serial")
public class AssinaturaNotaFiscalSaidaParcela extends ParcelaAB{
	
    private Long interfaceId;
    private AssinaturaNotaFiscalSaida notaFiscalSaida;
    
	public Long getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}
	public AssinaturaNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}
	public void setNotaFiscalSaida(AssinaturaNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}
	

}
