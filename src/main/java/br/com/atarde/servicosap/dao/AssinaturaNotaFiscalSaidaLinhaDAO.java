package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class AssinaturaNotaFiscalSaidaLinhaDAO {

	public AssinaturaNotaFiscalSaidaLinha inserirInterface(AssinaturaNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("assinaturanotafiscalsaida_linhas_id_seq"));
		
        broker.setPropertySQL("assinaturanotafiscalsaidalinhadao.inserirInterface",
        		model.getInterfaceId(),
        		model.getNotaFiscalSaida().getInterfaceId(),        		
        		model.getItem().getId(),
                model.getQuantidade(),
                model.getValorUnitario(),
                model.getValor(),
                model.getCodigoImposto().getId(),
                model.getCstCOFINS().getCodigo(),
                model.getCstICMS().getCodigo(),
                model.getCstIPI().getCodigo(),
                model.getCstPIS().getCodigo(),            
                model.getContaContabil().getId(),
                model.getCfop().getCodigo(),    
                model.getCodigoBarras(),
                model.getPedidoVendaLinha().getPedidoVenda().getId(),
                model.getPedidoVendaLinha().getNumero(),
                model.getUtilizacao().getId(),
                model.getVolume(),
                model.getFlagImposto());

        broker.execute();
        
        return model;
	}
	
	@SuppressWarnings("unchecked")
	public List<AssinaturaNotaFiscalSaidaLinha> pesquisarInterface(AssinaturaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("assinaturanotafiscalsaidalinhadao.pesquisarInterface", model.getInterfaceId());
		
		return broker.getCollectionBean(AssinaturaNotaFiscalSaidaLinha.class, "interfaceId", "notaFiscalSaida.interfaceId", "item.id", "quantidade",
																			   "valorUnitario", "valor", "codigoImposto.id","cstCOFINS.codigo",
																			   "cstICMS.codigo", "cstIPI.codigo", "cstPIS.codigo", "contaContabil.id",
																			   "cfop.codigo", "codigoBarras", "pedidoVendaLinha.pedidoVenda.id",
																			   "pedidoVendaLinha.numero", "utilizacao.id", "volume", "flagImposto");
	}	

}
