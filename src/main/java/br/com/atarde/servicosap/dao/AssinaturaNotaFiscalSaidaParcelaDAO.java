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
		
        broker.setSQL("INSERT INTO ASSINATURANOTAFISCALSAIDA_PARCELAS(ID, NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR) VALUES(?,?,?,?)",
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
		
		broker.setSQL("SELECT ID, NOTAFISCALSAIDA_ID, DATA_VENCIMENTO, VALOR FROM ASSINATURANOTAFISCALSAIDA_PARCELAS WHERE NOTAFISCALSAIDA_ID = ?", model.getInterfaceId());
		
		return broker.getCollectionBean(AssinaturaNotaFiscalSaidaParcela.class, "interfaceId", "notaFiscalSaida.interfaceId", "dataVencimento", "valor");
	}	

}
