package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Categoria;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CategoriaDAO {

	public Categoria obterPeloCodigo(Categoria model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("categoriadao.obterpelocodigo", model.getCodigo());

        return (Categoria) broker.getObjectBean(Categoria.class, "id");
	}

}
