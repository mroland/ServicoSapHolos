package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Boleto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class BoletoDAO {
	
	public Boleto obter(Boleto model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("boletosapdao.obter", model.getId());

		return (Boleto) broker.getObjectBean(Boleto.class, "id", "statusBoleto.id");
	}

}
