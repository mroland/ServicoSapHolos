package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Filial;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class FilialDAO {

	public Filial obter(Filial model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OBPL.\"BPLId\" id, OBPL.\"BPLName\" descricao, OBPL.\"MainBPL\" as flag_principal FROM " + model.getEmpresa().getDbInstancia() + ".OBPL WHERE OBPL.\"BPLId\" = ?", model.getId());

		return (Filial) broker.getObjectBean(Filial.class, "id", "descricao", "flagPrincipal");

	}

}
