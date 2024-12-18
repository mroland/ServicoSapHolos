package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class VendaAvulsaNotaFiscalSaidaRomaneioDAO {

	public void inserirInterface(VendaAvulsaNotaFiscalSaidaRomaneio model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("vendaavulsanotafiscalsaidaromaneios_id_seq"));

		broker.setPropertySQL("vendaavulsanotafiscalsaidaromaneiodao.inserirInterface", model.getInterfaceId(),model.getIdExterno(), model.getRoteiro(), model.getDescricao(), model.getData(), model.getReparte(), model.getEncalhe(), model.getVenda(), model.getPreco(), model.getValor(), model.getRdj(), model.getEmpresa().getId(), model.getNota().getInterfaceId(), model.getRegiao(), model.getCidade());

		broker.execute();
		
	}

	public void inserirInterfaceMSSQL(VendaAvulsaNotaFiscalSaidaRomaneio model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setPropertySQL("vendaavulsanotafiscalsaidaromaneiodao.inserirInterfaceMSSQL", model.getIdExterno(), model.getRoteiro(), model.getDescricao(), model.getData(), model.getReparte(), model.getEncalhe(), model.getVenda(), model.getPreco(), model.getValor(), model.getRdj(), model.getEmpresa().getId(), model.getNota().getId(), model.getRegiao(), model.getCidade());
		
		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<VendaAvulsaNotaFiscalSaidaRomaneio> pesquisarInterface(VendaAvulsaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("vendaavulsanotafiscalsaidaromaneiodao.pesquisarInterface",  model.getInterfaceId());
		
		return broker.getCollectionBean(VendaAvulsaNotaFiscalSaidaRomaneio.class, "interfaceId", "idExterno", "roteiro", "descricao", "data", "reparte", "encalhe", "venda", "preco", "valor", "rdj", "empresa.id", "nota.id", "regiao", "cidade");
				
	}

}
