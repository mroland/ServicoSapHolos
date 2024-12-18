package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@JsonInclude(Include.NON_NULL)
public abstract class NotaFiscalSaidaLinhaAB extends DocumentoLinhaAB implements Serializable {

	private Long interfaceId;
	private Boolean flagImposto;

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public Boolean getFlagImposto() {
		return flagImposto;
	}

	public void setFlagImposto(Boolean flagImposto) {
		this.flagImposto = flagImposto;
	}	
	
	
}
