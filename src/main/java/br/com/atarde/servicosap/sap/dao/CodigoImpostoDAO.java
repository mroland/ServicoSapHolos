package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.CodigoImposto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CodigoImpostoDAO {

	public CodigoImposto obter(CodigoImposto model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("codigoimpostodao.obter", model.getId());

        return (CodigoImposto) broker.getObjectBean(CodigoImposto.class, "id");
        
	}

}
