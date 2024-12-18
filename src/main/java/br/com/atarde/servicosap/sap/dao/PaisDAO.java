package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Pais;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class PaisDAO {

	public Pais obter(Pais model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("paisdao.obter", model.getId());
		
		return (Pais) broker.getObjectBean(Pais.class, "id");
		
	}

}
