package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.sap.model.ContasReceber;
import br.com.atarde.servicosap.sap.model.ContasReceberLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ContasReceberLinhaDAO {

	public void inserirComBrokerInterface(ContasReceberLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("contas_receber_linhas_id_seq"));
		
		broker.setPropertySQL("contasreceberlinhadaoI.inserirComBrokerInterface", model.getInterfaceId(), model.getParcela().getId(), model.getParcela().getNumero(),
				 													  model.getContasReceber().getInterfaceId(), model.getEmpresa().getId(), model.getValorAplicado());
		
		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<ContasReceberLinha> pesquisarInterface(ContasReceber model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("contasreceberlinhadaoI.pesquisarInterface", model.getInterfaceId());
		
		return broker.getCollectionBean(ContasReceberLinha.class, "id", "parcela.id", "parcela.numero", "contasReceber.interfaceId", "empresa.id", "valorAplicado");
	}

}
