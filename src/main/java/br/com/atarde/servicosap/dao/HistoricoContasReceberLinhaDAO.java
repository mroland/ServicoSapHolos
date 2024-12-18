package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.sap.model.ContasReceberLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoContasReceberLinhaDAO {

	public void inserirInterface(ContasReceberLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_contas_receber_linhas_id_seq"));
		
		broker.setPropertySQL("historicocontasreceberlinhadaoI.inserirInterface", model.getInterfaceId(), model.getParcela().getId(), model.getParcela().getNumero(),
				 													  model.getContasReceber().getInterfaceId(), model.getEmpresa().getId(), model.getValorAplicado());
		
		broker.execute();
		
	}

}
