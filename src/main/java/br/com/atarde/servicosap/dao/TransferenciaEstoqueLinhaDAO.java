package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.TransferenciaEstoqueLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class TransferenciaEstoqueLinhaDAO {

	public void inserirInterface(TransferenciaEstoqueLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("transferencia_estoque_linha_id_seq"));

		broker.setSQL("INSERT INTO PUBLIC.TRANSFERENCIA_ESTOQUE_LINHA(ID, TRANSFERENCIA_ESTOQUE_ID, ITEM_ID, QUANTIDADE, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID) VALUES(?,?,?,?,?,?);", model.getInterfaceId(), model.getTransferenciaEstoque().getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getEstoqueOrigem().getId(), model.getEstoqueDestino().getId());

		broker.execute();

	}

}
