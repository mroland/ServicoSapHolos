package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class NotaFiscalSaidaDAO {
	
	public NotaFiscalSaida obterIdExterno(NotaFiscalSaidaAB model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("notafiscalsaidadao.obterIdExterno", model.getIdExterno(), model.getOrigem().getId());
		
		return (NotaFiscalSaida) broker.getObjectBean(NotaFiscalSaida.class, "id");
		
	}	

}
