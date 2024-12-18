package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class EasyclassNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(EasyclassNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("easyclassnotafiscalsaida_linhas_id_seq"));

		broker.setPropertySQL("easyclassnotafiscalsaidalinhadao.inserirInterface", model.getInterfaceId(), model.getNotaFiscalSaida().getInterfaceId(), 
																			model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(),
				                                                            model.getValor(), model.getCodigoImposto().getId(), 
				                                                            model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), 
				                                                            model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), 
				                                                            model.getUCmXCol(), model.getUArea(), model.getUQuantidadeInsercoes(),
				                                                            model.getUTotalCmXCol(), model.getUValorUnitario(),
				                                                            model.getCodigoBarras(), model.getPedidoVendaLinha().getPedidoVenda().getId(),
				                                                            model.getPedidoVendaLinha().getNumero(),
				                                                            model.getUtilizacao().getId(), model.getVolume());

		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<EasyclassNotaFiscalSaidaLinha> pesquisarInterface(EasyclassNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("easyclassnotafiscalsaidalinhadao.pesquisarInterface", model.getInterfaceId());
		
		return broker.getCollectionBean(EasyclassNotaFiscalSaidaLinha.class, "interfaceId", "notaFiscalSaida.id", "item.id", "quantidade",
																			   "valorUnitario", "valor", "codigoImposto.id","cstCOFINS.codigo",
																			   "cstICMS.codigo", "cstIPI.codigo", "cstPIS.codigo", 
																			   "uCmXCol", "uArea", "uQuantidadeInsercoes", "uTotalCmXCol","uValorUnitario",
																			   "codigoBarras", "pedidoVendaLinha.pedidoVenda.id",
																			   "pedidoVendaLinha.numero", "utilizacao.id", "volume");
	}

}
