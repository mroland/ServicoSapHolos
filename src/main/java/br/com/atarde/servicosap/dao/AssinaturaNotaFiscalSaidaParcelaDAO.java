package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaidaParcela;
import br.com.atarde.servicosap.sap.model.ParcelaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class AssinaturaNotaFiscalSaidaParcelaDAO {

	public AssinaturaNotaFiscalSaidaParcela inserirInterface(AssinaturaNotaFiscalSaidaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("assinaturanotafiscalsaida_parcelas_id_seq"));
		
        broker.setPropertySQL("assinaturanotafiscalsaidaparceladao.inserirInterface",
        		model.getInterfaceId(),
        		model.getNotaFiscalSaida().getInterfaceId(),        		
        		model.getDataVencimento(),
                model.getValor());

        broker.execute();
        
        return model;
	}
	
	@SuppressWarnings("unchecked")
	public List<ParcelaAB> pesquisarInterface(AssinaturaNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("assinaturanotafiscalsaidaparceladao.pesquisarInterface", model.getInterfaceId());
		
		return broker.getCollectionBean(AssinaturaNotaFiscalSaidaParcela.class, "interfaceId", "notaFiscalSaida.interfaceId", "dataVencimento", "valor");
	}	

}
