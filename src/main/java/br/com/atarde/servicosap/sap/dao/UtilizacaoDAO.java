package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Utilizacao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class UtilizacaoDAO {

	public Utilizacao obter(Utilizacao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("utilizacaodao.obter", model.getId());
		
		return (Utilizacao) broker.getObjectBean(Utilizacao.class, "id");
	}

}
