package br.com.atarde.servicosap.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.atarde.servicosap.sap.model.ContasReceber;
import br.com.atarde.servicosap.sap.model.ContasReceberLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class ContasReceberDAO {

    @SuppressWarnings("unchecked")
    public List<ContasReceber> pesquisarInterface(ContasReceber model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setPropertySQL("contasreceberdaoI.pesquisarInterface", model.getEmpresa().getId());

        return broker.getCollectionBean(ContasReceber.class, "interfaceId", "cliente.id", "dataLancamento", "dataDocumento",
                "dataVencimento", "valor", "status.id", "tipoModalidade", "mensagemErro", "dataExportacao", "empresa.id", "dataImportacao");
    }

    public void alterarInterface(ContasReceber model) throws TSApplicationException {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

        broker.setPropertySQL("contasreceberdaoI.alterarInterface", model.getStatus().getId(), model.getMensagemErro(), model.getInterfaceId());

        broker.execute();

    }

    @SuppressWarnings("unchecked")
	public List<ContasReceber> pesquisarPorAtrasadaInterface(ContasReceber model) {
        
        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
        
        broker.setPropertySQL("contasreceberdaoI.pesquisarPorAtrasadaInterface", model.getStatus().getId());
        
        return broker.getCollectionBean(ContasReceber.class, "interfaceId");
    }

	public ContasReceber inserirInterface(ContasReceber model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.beginTransaction();
		
		model.setInterfaceId(broker.getSequenceNextValue("contas_receber_id_seq"));
		
		broker.setPropertySQL("contasreceberdaoI.inserirInterface", model.getInterfaceId(), model.getCliente().getId(), model.getDataLancamento(), 
				                                           model.getDataDocumento(), model.getValor(), model.getStatus().getId(), 
				                                           model.getTipoModalidade(), model.getMensagemErro(), new Timestamp(System.currentTimeMillis()),
				                                           model.getDataVencimento(), model.getEmpresa().getId(), model.getDataImportacao());
		
		broker.execute();
		
		for (ContasReceberLinha linha : model.getLinhas()) {
			
			linha.setContasReceber(new ContasReceber("interfaceId",model.getInterfaceId()));
			
			linha.setEmpresa(model.getEmpresa());

			new ContasReceberLinhaDAO().inserirComBrokerInterface(linha,broker);
			
		}
		
		if(!TSUtil.isEmpty(model.getModalidadePagamentoBoleto())){
			
			model.getModalidadePagamentoBoleto().setContasReceber(new ContasReceber("interfaceId",model.getInterfaceId()));
			
			new ModalidadePagamentoBoletoDAO().inserirComBrokerInterface(model.getModalidadePagamentoBoleto(), broker);
			
		}
		
		if(!TSUtil.isEmpty(model.getModalidadePagamentoTransferencia())){
			
			model.getModalidadePagamentoTransferencia().setContasReceber(new ContasReceber("interfaceId",model.getInterfaceId()));
			
			new ModalidadePagamentoTransferenciaDAO().inserirComBrokerInterface(model.getModalidadePagamentoTransferencia(),broker);
			
		}
		

		broker.endTransaction();

		return model;
		
	}

	public void excluirInterface(ContasReceber model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("contasreceberdaoI.excluirInterface", model.getInterfaceId());
		
		broker.execute();
		
	}

}
