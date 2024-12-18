package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoVendaAvulsaNotaFiscalSaidaRomaneioDAO {

	public void inserirInterface(VendaAvulsaNotaFiscalSaidaRomaneio model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_vendaavulsanotafiscalsaidaromaneios_id_seq"));

		broker.setPropertySQL("historicovendaavulsanotafiscalsaidaromaneiodao.inserirInterface", model.getInterfaceId(),model.getIdExterno(), model.getRoteiro(), model.getDescricao(), model.getData(), model.getReparte(), model.getEncalhe(), model.getVenda(), model.getPreco(), model.getValor(), model.getRdj(), model.getEmpresa().getId(), model.getNota().getInterfaceId(), model.getNota().getId(), model.getRegiao(), model.getCidade());

		broker.execute();
		
	}


}
