package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.util.Constantes;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class EmpresaDAO {

	public Empresa obter(Empresa model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(Constantes.JNDI_SABWEB);

		broker.setPropertySQL("empresadao.obter", model.getId());

		return (Empresa) broker.getObjectBean(Empresa.class, "id", "descricao", "flagAtivo", "jndi", "servidor", "dbInstancia", "dbUsuario", "dbSenha", "appUsuario", "appSenha", "servidorLicenca", "portaLicenca", "cnpj");
		
	}


}
