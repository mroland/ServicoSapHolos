package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Origem;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class OrigemDAO {

	public Origem obter(Origem model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("origemdao.obter", model.getId());
		
		return (Origem) broker.getObjectBean(Origem.class, "id", "descricao");
		
	}

}
