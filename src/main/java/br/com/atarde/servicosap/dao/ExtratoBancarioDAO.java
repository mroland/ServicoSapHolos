package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.sap.model.ExtratoBancario;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

public class ExtratoBancarioDAO {

	@SuppressWarnings("unchecked")
	public List<ExtratoBancario> pesquisarInterface(ExtratoBancario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("extratobancariodao.pesquisarInterface", model.getEmpresa().getId());

		return broker.getCollectionBean(ExtratoBancario.class, "interfaceId", "conta", "dataVencimento", "numeroDocumento", "detalhe", "valorDebito", "contaContabil.id", "codigoInterno.id", "valorCredito", "empresa.id", "status.id", "dataImportacao", "dataExportacao", "mensagemErro", "dataAtualizacao");

	}

	public void alterarInterface(ExtratoBancario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("extratobancariodao.alterarInterface", model.getStatus().getId(), model.getMensagemErro(), model.getDataAtualizacao(), model.getDataImportacao(), model.getInterfaceId());

		broker.execute();

	}

	public void excluirInterface(ExtratoBancario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("extratobancariodao.excluirInterface", model.getInterfaceId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<ExtratoBancario> pesquisarPorAtrasadaInterface(ExtratoBancario model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("extratobancariodao.pesquisarporextratoatrasadoInterface");

		return broker.getCollectionBean(ExtratoBancario.class, "interfaceId", "dataAtualizacao", "dataImportacao");

	}

	public ExtratoBancario inserirInterface(ExtratoBancario model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("extrato_bancarios_id_seq"));

		broker.setPropertySQL("extratobancariodao.inserirInterface", model.getInterfaceId(), model.getConta(), model.getDataVencimento(), model.getNumeroDocumento(), model.getDetalhe(), model.getValorDebito(), model.getContaContabil().getId(), model.getCodigoInterno().getId(), model.getValorCredito(), model.getEmpresa().getId(), model.getStatus().getId(), model.getDataImportacao(), model.getDataExportacao(), model.getMensagemErro(), model.getDataAtualizacao());

		broker.execute();

		broker.endTransaction();

		return model;
	}

}
