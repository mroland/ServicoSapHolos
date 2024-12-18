package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaidaLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoEasyclassNotaFiscalSaidaLinhaDAO {

	public void inserirInterface(EasyclassNotaFiscalSaidaLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_easyclassnotafiscalsaida_linhas_id_seq"));

		broker.setPropertySQL("historicoeasyclassnotafiscalsaidalinhadao.inserirInterface", model.getInterfaceId(), model.getNotaFiscalSaida().getInterfaceId(), 
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

}
