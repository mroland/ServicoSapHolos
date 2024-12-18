package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.HistoricoContabilidade;
import br.com.atarde.servicosap.sap.model.Contabilidade;
import br.com.atarde.servicosap.sap.model.ContabilidadeLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoContabilidadeDAO {

	public void inserirInterface(HistoricoContabilidade model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_contabilidades_id_seq"));
		
		broker.setPropertySQL("historicocontabilidadedao.inserir", model.getInterfaceId(), model.getDataVencimento(), model.getObservacao(), model.getDataLancamento(), model.getDataDocumento(),
				                  model.getEmpresa().getId(), model.getStatus().getId(), model.getDataImportacao(), model.getDataExportacao(), model.getMensagemErro(), model.getDataAtualizacao(), model.getId(), model.getReferencia2());
		
		broker.execute();
		
		for (ContabilidadeLinha item : model.getLinhas()) {
			
			item.setContabilidade(new Contabilidade());
			
			item.getContabilidade().setInterfaceId(model.getInterfaceId());
			
			new HistoricoContabilidadeLinhaDAO().inserir(item, broker);
			
		}
		
		broker.endTransaction();
		
	}

}
