package br.com.atarde.servicosap.sap.dao;

import java.util.List;

import br.com.atarde.servicosap.sap.model.CondicaoPagamento;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class CondicaoPagamentoDAO {

	public CondicaoPagamento obter(CondicaoPagamento model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("condicaopagamentodao.obter", model.getId());

        return (CondicaoPagamento) broker.getObjectBean(CondicaoPagamento.class, "id", "quantidadeParcelas");
	}

	@SuppressWarnings("unchecked")
	public List<CondicaoPagamento> pesquisar(CondicaoPagamento model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("condicaopagamentodao.pesquisar");

        return broker.getCollectionBean(CondicaoPagamento.class, "id", "descricao", "diasVencimento");
	
	}

}
