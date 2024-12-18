package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.sap.model.ContasReceber;
import br.com.atarde.servicosap.sap.model.ModalidadePagamentoBoleto;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ModalidadePagamentoBoletoDAO {

    public ModalidadePagamentoBoleto obter(ContasReceber model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setPropertySQL("modalidadepagamentoboletodaoI.obterporcontasreceberInterface", model.getInterfaceId());

        return (ModalidadePagamentoBoleto) broker.getObjectBean(ModalidadePagamentoBoleto.class, "interfaceId", "valor", "formaPagamento.id", "referencia", "dataVencimento", "contasReceber.interfaceId");

    }

	public void inserirComBrokerInterface(ModalidadePagamentoBoleto model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("modalidade_pagamento_boleto_id_seq"));
		
		broker.setPropertySQL("modalidadepagamentoboletodaoI.inserirComBrokerInterface", model.getInterfaceId(), model.getContasReceber().getInterfaceId(),
																			    model.getValor(), model.getFormaPagamento().getId(), model.getReferencia(),
																			    model.getDataVencimento());
		
		broker.execute();
		
	}
}
