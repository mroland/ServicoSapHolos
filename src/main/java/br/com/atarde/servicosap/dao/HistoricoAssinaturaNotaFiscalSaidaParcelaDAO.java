package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaidaParcela;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoAssinaturaNotaFiscalSaidaParcelaDAO {

	public AssinaturaNotaFiscalSaidaParcela inserirInterface(AssinaturaNotaFiscalSaidaParcela model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("historico_assinaturanotafiscalsaida_parcelas_id_seq"));
		
        broker.setPropertySQL("historicoassinaturanotafiscalsaidaparceladao.inserirInterface",
        		model.getInterfaceId(),
        		model.getNotaFiscalSaida().getInterfaceId(),        		
        		model.getDataVencimento(),
                model.getValor());

        broker.execute();
        
        return model;
	}
	
}
