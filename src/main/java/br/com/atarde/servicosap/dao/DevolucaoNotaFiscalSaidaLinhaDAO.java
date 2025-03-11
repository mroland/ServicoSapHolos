package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.DevolucaoNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class DevolucaoNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(DevolucaoNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("devolucao_notafiscalsaida_linhas_id_seq"));

		broker.setSQL("INSERT INTO DEVOLUCAO_NOTAFISCALSAIDA_LINHAS(ID, DEVOLUCAO_NOTAFISCALSAIDA_ID, ITEM_ID, QUANTIDADE, VALOR_UNITARIO, VALOR, CODIGO_IMPOSTO_ID, CST_COFINS, CST_ICMS, CST_IPI, CST_PIS, CONTA_CONTABIL_ID, CFOP, CODIGO_BARRAS, UTILIZACAO_ID, VOLUME, FLAG_IMPOSTO, DEPOSITO_ID, UNIDADE_NEGOCIO_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getDevolucaoNotaFiscalSaida().getInterfaceId(), 
																			model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(),
				                                                            model.getValor(), model.getCodigoImposto().getId(), 
				                                                            model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), 
				                                                            model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), 
				                                                            model.getContaContabil().getId(),model.getCfop().getCodigo(),
				                                                            model.getCodigoBarras(), model.getUtilizacao().getId(), model.getVolume(), model.getFlagImposto(), model.getEstoque().getId(), model.getUnidadeNegocio().getId());

		broker.execute();
		
	}

}
