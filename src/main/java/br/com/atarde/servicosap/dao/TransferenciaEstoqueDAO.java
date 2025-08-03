package br.com.atarde.servicosap.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosap.model.TransferenciaEstoque;
import br.com.atarde.servicosap.model.TransferenciaEstoqueLinha;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TransferenciaEstoqueDAO {

	public void inserirInterface(TransferenciaEstoque model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("transferencia_estoque_id_seq"));

		broker.setSQL("INSERT INTO TRANSFERENCIA_ESTOQUE(ID, ID_EXTERNO, DATA_DOCUMENTO, DATA_LANCAMENTO, DATA_VENCIMENTO, OBSERVACAO_DIARIO, OBSERVACAO, ESTOQUE_ORIGEM_ID, ESTOQUE_DESTINO_ID, EMPRESA_ID, ORIGEM_ID, FILIAL_ID, DATA_EXPORTACAO, NOTA_FISCAL_SAIDA_REFERENCIA_ID, DEVOLUCAO_NOTA_FISCAL_SAIDA_REFERENCIA_ID, ARQUIVO_REMESSA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", model.getInterfaceId(), model.getIdExterno(), model.getDataDocumento(), model.getDataLancamento(), model.getDataVencimento(), model.getObservacaoDiario(), model.getObservacao(), model.getEstoqueOrigem().getId(), model.getEstoqueDestino().getId(), model.getEmpresa().getId(), model.getOrigem().getId(), model.getFilial().getId(), new Timestamp(model.getDataExportacao().getTime()), TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada()) ? null : model.getNotaFiscalSaidaReferenciada().getId(), TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada()) ? null : model.getDevolucaoNotaFiscalSaidaReferenciada().getId(), model.getArquivoRemessa());

		broker.execute();

		for (TransferenciaEstoqueLinha linha : model.getLinhas()) {

			linha.setTransferenciaEstoque(new TransferenciaEstoque(model.getInterfaceId()));

			new TransferenciaEstoqueLinhaDAO().inserirInterface(linha, broker);

		}

	}

	public TransferenciaEstoque inserirInterface(TransferenciaEstoque model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		this.inserirInterface(model, broker);

		broker.endTransaction();

		return model;

	}

	public TransferenciaEstoque obterIdExternoInterface(TransferenciaEstoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM TRANSFERENCIA_ESTOQUE WHERE ID_EXTERNO = ? AND EMPRESA_ID = ?", model.getIdExterno(), model.getEmpresa().getId());

		return (TransferenciaEstoque) broker.getObjectBean(TransferenciaEstoque.class, "interfaceId");

	}

}
