package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.sap.model.ModalidadePagamentoTransferencia;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoModalidadePagamentoTransferenciaDAO {

	public void inserirInterface(ModalidadePagamentoTransferencia model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_modalidade_pagamento_transferencia_id_seq"));
		
		broker.setPropertySQL("historicomodalidadepagamentotransferenciadaoI.inserirInterface", model.getInterfaceId(), model.getContasReceber().getInterfaceId(), model.getValor(),
																					   model.getContaContabil().getId(), model.getReferencia(), model.getDataTransferencia());
		
		broker.execute();
		
	}

}
