package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.sap.model.ModalidadePagamentoBoleto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoModalidadePagamentoBoletoDAO {

	public void inserirInterface(ModalidadePagamentoBoleto model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_modalidade_pagamento_boleto_id_seq"));
		
		broker.setPropertySQL("historicomodalidadepagamentoboletodaoI.inserirInterface", model.getInterfaceId(), model.getContasReceber().getInterfaceId(),
																			    model.getValor(), model.getFormaPagamento().getId(), model.getReferencia(),
																			    model.getDataVencimento());
		
		broker.execute();
		
	}
}
