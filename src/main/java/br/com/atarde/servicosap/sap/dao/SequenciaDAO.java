package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Sequencia;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class SequenciaDAO {

	public Sequencia obter(Sequencia model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("sequenciadao.obter", model.getId());

        return (Sequencia) broker.getObjectBean(Sequencia.class, "id");
	}

}
