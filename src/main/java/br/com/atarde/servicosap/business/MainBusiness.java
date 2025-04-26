package br.com.atarde.servicosap.business;

import br.com.topsys.exception.TSApplicationException;

public abstract class MainBusiness<T> {

	public T insertBatchEvent(T model) throws TSApplicationException{

		String retorno;

		retorno = this.validar(model);

		if ("".equals(retorno)) {

			return this.inserirLote(model);

		} else {
			
			return model;

		}

	}

	public abstract String validar(T model);
	
	public abstract T inserirLote(T model) throws TSApplicationException;
	

}
