package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.CST;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CstDAO {

	public CST obterPeloCodigo(CST model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("cstdao.obterpelocodigo", model.getCodigo(), model.getCategoria().getId());

        return (CST) broker.getObjectBean(CST.class, "codigo");
	}

}
