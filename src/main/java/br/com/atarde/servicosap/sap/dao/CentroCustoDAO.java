package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.CentroCusto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CentroCustoDAO {

	public CentroCusto obter(CentroCusto model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("centrocustosapdao.obter", model.getDimensao().getId(), model.getId());

		return (CentroCusto) broker.getObjectBean(CentroCusto.class, "id");
	}

}
