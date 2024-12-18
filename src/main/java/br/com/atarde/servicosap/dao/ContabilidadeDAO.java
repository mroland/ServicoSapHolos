package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.sap.model.Contabilidade;
import br.com.atarde.servicosap.sap.model.ContabilidadeLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ContabilidadeDAO {

	@SuppressWarnings("unchecked")
	public List<Contabilidade> pesquisarInterface(Contabilidade model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("contabilidadedao.pesquisarInterface", model.getEmpresa().getId());
		
		return broker.getCollectionBean(Contabilidade.class, "interfaceId", "dataVencimento", "observacao", "dataLancamento" , "dataDocumento", 
				                                           "empresa.id", "status.id", "dataImportacao", "dataExportacao", "mensagemErro",
				                                           "dataAtualizacao", "id", "referencia2");

	}

	public void alterarInterface(Contabilidade model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("contabilidadedao.alterarInterface", model.getStatus().getId(), model.getMensagemErro(), model.getDataAtualizacao(), model.getDataImportacao(), model.getInterfaceId());
				
		broker.execute();
		
	}

	public void excluirInterface(Contabilidade model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("contabilidadedao.excluirInterface", model.getInterfaceId());
				
		broker.execute();

		
	}

	@SuppressWarnings("unchecked")
	public List<Contabilidade> pesquisarPorAtrasadaInterface(Contabilidade model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("contabilidadedao.pesquisarpornotaatrasadaInterface");
		
		return broker.getCollectionBean(Contabilidade.class, "interfaceId", "dataAtualizacao", "dataImportacao");
		
	}

	public Contabilidade inserirInterface(Contabilidade model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("contabilidades_id_seq"));
		
		broker.setPropertySQL("contabilidadedao.inserirInterface", model.getInterfaceId(), model.getDataVencimento(), model.getObservacao(), model.getDataLancamento(),model.getDataDocumento(),
				                                          model.getEmpresa().getId(), model.getStatus().getId(), model.getDataImportacao(), model.getDataExportacao(), 
				                                          model.getMensagemErro(), model.getDataAtualizacao(), model.getId(),model.getReferencia2());
		
		broker.execute();
		
		for (ContabilidadeLinha linha : model.getLinhas()) {
			
			linha.setContabilidade(new Contabilidade("interfaceId", model.getInterfaceId()));
			
			new ContabilidadeLinhaDAO().inserirInterface(linha,broker);
			
		}
		
		broker.endTransaction();

		return model;
	}

}
