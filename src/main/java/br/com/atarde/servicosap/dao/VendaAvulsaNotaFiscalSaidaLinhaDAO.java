package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class VendaAvulsaNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(VendaAvulsaNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("vendaavulsanotafiscalsaida_linhas_id_seq"));

		broker.setPropertySQL("vendaavulsanotafiscalsaidalinhadao.inserirInterface", model.getInterfaceId(), model.getNotaFiscalSaida().getInterfaceId(), 
																			model.getItem().getId(), model.getQuantidade(), model.getValorUnitario(),
				                                                            model.getValor(), model.getCodigoImposto().getId(), 
				                                                            model.getCstCOFINS().getCodigo(), model.getCstICMS().getCodigo(), 
				                                                            model.getCstIPI().getCodigo(), model.getCstPIS().getCodigo(), 
				                                                            model.getContaContabil().getId(),model.getCfop().getCodigo(),
				                                                            model.getCodigoBarras(), model.getPedidoVendaLinha().getPedidoVenda().getId(),
				                                                            model.getPedidoVendaLinha().getNumero(),
				                                                            model.getUtilizacao().getId(), model.getVolume(), model.getFlagImposto());

		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<VendaAvulsaNotaFiscalSaidaLinha> pesquisarInterface(VendaAvulsaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("vendaavulsanotafiscalsaidalinhadao.pesquisarInterface", model.getInterfaceId());
		
		return broker.getCollectionBean(VendaAvulsaNotaFiscalSaidaLinha.class, "interfaceId", "notaFiscalSaida.interfaceId", "item.id", "quantidade",
																			   "valorUnitario", "valor", "codigoImposto.id","cstCOFINS.codigo",
																			   "cstICMS.codigo", "cstIPI.codigo", "cstPIS.codigo", "contaContabil.id",
																			   "cfop.codigo", "codigoBarras", "pedidoVendaLinha.pedidoVenda.id",
																			   "pedidoVendaLinha.numero", "utilizacao.id", "volume", "flagImposto");
	}

}
