package br.com.atarde.servicosap.dao;

import java.sql.Timestamp;

import br.com.atarde.servicosap.model.TabelaUsuarioMovimentacao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TabelaUsuarioMovimentacaoDAO {

	public void inserirInterface(TabelaUsuarioMovimentacao model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("tabela_usuario_movimentacao_id_seq"));

		broker.setSQL("INSERT INTO TABELA_USUARIO_MOVIMENTACAO(ID, ITEM_ID, QUANTIDADE, TIPO_MOVIMENTACAO_ID, ID_EXTERNO, EMPRESA_ID, FILIAL_ID, DATA_EXPORTACAO, NOTA_FISCAL_SAIDA_REFERENCIA_ID, DEVOLUCAO_NOTA_FISCAL_SAIDA_REFERENCIA_ID, ARQUIVO_REMESSA, SAP_NOTA_FISCAL_ID, SAP_DEVOLUCAO_NOTA_FISCAL_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getItem().getId(), model.getQuantidade(), model.getTipoMovimentacao(), model.getIdExterno(), model.getEmpresa().getId(), model.getFilial().getId(), new Timestamp(System.currentTimeMillis()), TSUtil.isEmpty(model.getNotaFiscalSaidaReferenciada()) ? null :  model.getNotaFiscalSaidaReferenciada().getId(), TSUtil.isEmpty(model.getDevolucaoNotaFiscalSaidaReferenciada()) ? null : model.getDevolucaoNotaFiscalSaidaReferenciada().getId(), model.getArquivoRemessa(), null, null);

		broker.execute();
	}

}
