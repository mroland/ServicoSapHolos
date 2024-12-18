package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Estado;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class EstadoDAO {

	public Estado obter(Estado model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("estadodao.obter", model.getId());
		
		return (Estado) broker.getObjectBean(Estado.class, "id");
		
	}

}
