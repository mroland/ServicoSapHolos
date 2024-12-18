package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.sap.model.ContasReceber;
import br.com.atarde.servicosap.sap.model.ModalidadePagamentoTransferencia;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ModalidadePagamentoTransferenciaDAO {

	public ModalidadePagamentoTransferencia obter(ContasReceber model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("modalidadepagamentotransferenciadaoI.obterporcontasreceberInterface", model.getInterfaceId());
		
		return (ModalidadePagamentoTransferencia) broker.getObjectBean(ModalidadePagamentoTransferencia.class, "interfaceId", "valor", "contaContabil.id", "referencia", "dataTransferencia", "contasReceber.interfaceId");
	}

	public void inserirComBrokerInterface(ModalidadePagamentoTransferencia model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("modalidade_pagamento_transferencia_id_seq"));
		
		broker.setPropertySQL("modalidadepagamentotransferenciadaoI.inserirComBrokerInterface", model.getInterfaceId(), model.getContasReceber().getInterfaceId(), model.getValor(),
																					   model.getContaContabil().getId(), model.getReferencia(), model.getDataTransferencia());
		
		broker.execute();
		
	}

}
