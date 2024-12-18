package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.HistoricoExtratoBancario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoExtratoBancarioDAO {

	public void inserirInterface(HistoricoExtratoBancario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_extrato_bancarios_id_seq"));
		
		broker.setPropertySQL("historicoextratobancariodao.inserir", model.getInterfaceId(), model.getConta(), model.getDataVencimento(), model.getNumeroDocumento(), model.getDetalhe(), model.getValorDebito(), model.getContaContabil().getId(), model.getCodigoInterno().getId(), model.getValorCredito(), model.getEmpresa().getId(), model.getStatus().getId(), model.getDataImportacao(), model.getDataExportacao(), model.getMensagemErro(), model.getDataAtualizacao());
		
		broker.execute();
		
		broker.endTransaction();
		
	}

}
