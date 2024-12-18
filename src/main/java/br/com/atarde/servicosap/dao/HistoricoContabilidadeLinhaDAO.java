package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.sap.model.ContabilidadeLinha;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;

public class HistoricoContabilidadeLinhaDAO {

	public void inserir(ContabilidadeLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setInterfaceId(broker.getSequenceNextValue("historico_contabilidades_linhas_id_seq"));

		broker.setPropertySQL("historicocontabilidadelinhadao.inserir", model.getInterfaceId(), model.getNumero(), model.getContabilidade().getInterfaceId(), model.getValorCredito(), model.getValorDebito(), model.getDataVencimento(), model.getDataLancamento(), model.getCodigoProjeto(), model.getReferencia1(), model.getReferencia2(), model.getContaContabil().getId(), model.getCentroCusto().getId(), model.getParceiroNegocio().getId(), model.getParceiroNegocio()
				.getIdentificadorFiscal().getTipoIdentificador(), model.getParceiroNegocio().getIdentificadorFiscal().getIdentificador(), model.getParceiroNegocio().getNome(), model.getParceiroNegocio().getNomeFantasia(), model.getParceiroNegocio().getTelefoneResidencial(), model.getParceiroNegocio().getTelefoneCelular(), model.getParceiroNegocio().getFax(), model.getParceiroNegocio().getEmail(), model.getParceiroNegocio().getObservacao(), model.getParceiroNegocio().getEndereco()
				.getLogradouro(), model.getParceiroNegocio().getEndereco().getNumero(), model.getParceiroNegocio().getEndereco().getComplemento(), model.getParceiroNegocio().getEndereco().getBairro(), model.getParceiroNegocio().getEndereco().getCidade(), model.getParceiroNegocio().getEndereco().getEstado().getId(), model.getParceiroNegocio().getEndereco().getCep(), model.getParceiroNegocio().getEndereco().getPais().getId(), model.getParceiroNegocio().getEndereco().getMunicipio().getId(), model
				.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadual(), model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoMunicipal(), model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoINSS(), model.getParceiroNegocio().getDataAtualizacao(), model.getParceiroNegocio().getClassificacao().getId(), model.getParceiroNegocio().getTipo(),
				model.getParceiroNegocio().getFlagEndereco(), 	model.getParceiroNegocio().getEnderecoEntregaDefault(), model.getParceiroNegocio().getEnderecoCobrancaDefault(), model.getObservacao());

		broker.execute();

	}

}
