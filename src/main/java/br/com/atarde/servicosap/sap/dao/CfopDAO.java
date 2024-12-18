package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.CFOP;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CfopDAO {

	public CFOP obterPeloCodigo(CFOP model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("cfopdao.obterpelocodigo", model.getCodigo());

        return (CFOP) broker.getObjectBean(CFOP.class, "codigo");
        
	}

}
