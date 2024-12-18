package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.GrupoComissao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class GrupoComissaoDAO {

	public GrupoComissao obter(GrupoComissao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("grupocomissaodao.obter", model.getId());
	
		return (GrupoComissao) broker.getObjectBean(GrupoComissao.class, "id", "descricao"); 
		
	}

}
