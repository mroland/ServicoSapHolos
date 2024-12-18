package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.CodigoInterno;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CodigoInternoDAO {

	public CodigoInterno obter(CodigoInterno model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("codigointernodao.obter", model.getId());

		return (CodigoInterno) broker.getObjectBean(CodigoInterno.class, "id", "descricao");
	}

	
}
