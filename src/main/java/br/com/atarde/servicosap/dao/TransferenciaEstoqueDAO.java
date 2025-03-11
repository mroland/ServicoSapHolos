package br.com.atarde.servicosap.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosap.model.TransferenciaEstoque;
import br.com.atarde.servicosap.model.TransferenciaEstoqueLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TransferenciaEstoqueDAO {

	public void inserirInterface(TransferenciaEstoque model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("transferencia_estoque_id_seq"));

		broker.setSQL("INSERT INTO TRANSFERENCIA_ESTOQUE(ID, ID_EXTERNO, DATA_DOCUMENTO, DATA_LANCAMENTO, DATA_VENCIMENTO, OBSERVACAO_DIARIO, OBSERVACAO, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID, EMPRESA_ID, ORIGEM_ID, FILIAL_ID, DATA_EXPORTACAO, NOTA_FISCAL_SAIDA_REFERENCIA_ID, DEVOLUCAO_NOTA_FISCAL_SAIDA_REFERENCIA_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", model.getInterfaceId(), model.getIdExterno(), model.getDataDocumento(), model.getDataLancamento(), model.getDataVencimento(), model.getObservacaoDiario(), model.getObservacao(), model.getEstoqueOrigem().getId(), model.getEstoqueDestino().getId(), model.getEmpresa().getId(), model.getOrigem().getId(), model.getFilial().getId(), new Timestamp(model.getDataExportacao().getTime()), TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada()) ? null :  model.getNotaFiscalSaidaReferenciada().getId(), TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada()) ? null : model.getDevolucaoNotaFiscalSaidaReferenciada().getId());

		broker.execute();

		for (TransferenciaEstoqueLinha linha : model.getLinhas()) {

			linha.setTransferenciaEstoque(new TransferenciaEstoque(model.getInterfaceId()));

			new TransferenciaEstoqueLinhaDAO().inserirInterface(linha, broker);

		}

	}

}
