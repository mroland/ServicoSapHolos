package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClassificacaoItem implements Serializable{

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
