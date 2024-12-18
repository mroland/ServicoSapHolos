package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.sap.model.Contabilidade;
import br.com.atarde.servicosap.sap.model.ContabilidadeLinha;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;


public class ContabilidadeLinhaDAO {

	@SuppressWarnings("unchecked")
	public List<ContabilidadeLinha> pesquisarInterface(Contabilidade model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("contabilidadelinhadao.pesquisarInterface", model.getInterfaceId());
		
		return broker.getCollectionBean(ContabilidadeLinha.class, "interfaceId", "numero", "contabilidade.interfaceId","valorCredito", "valorDebito", "dataVencimento", 
				 												  "dataLancamento", "codigoProjeto", "referencia1", "referencia2",
				 												  "contaContabil.id", "centroCusto.id", "parceiroNegocio.id", 
				 												 "parceiroNegocio.identificadorFiscal.tipoIdentificador", "parceiroNegocio.identificadorFiscal.identificador",
				 								                "parceiroNegocio.nome", "parceiroNegocio.nomeFantasia", "parceiroNegocio.telefoneResidencial", "parceiroNegocio.telefoneCelular",
				 								                "parceiroNegocio.fax", "parceiroNegocio.email", "parceiroNegocio.observacao", "parceiroNegocio.endereco.logradouro",
				 								                "parceiroNegocio.endereco.numero", "parceiroNegocio.endereco.complemento", "parceiroNegocio.endereco.bairro",
				 								                "parceiroNegocio.endereco.cidade", "parceiroNegocio.endereco.estado.id", "parceiroNegocio.endereco.cep", "parceiroNegocio.endereco.pais.id",
				 								                "parceiroNegocio.endereco.municipio.id", "parceiroNegocio.identificadorFiscal.inscricaoEstadual", "parceiroNegocio.identificadorFiscal.inscricaoEstadualSubstitutoTributaria",
				 								                "parceiroNegocio.identificadorFiscal.inscricaoMunicipal", "parceiroNegocio.identificadorFiscal.inscricaoINSS",
				 								                "parceiroNegocio.dataAtualizacao", "parceiroNegocio.classificacao.id", "parceiroNegocio.tipo", "parceiroNegocio.flagEndereco","parceiroNegocio.enderecoEntregaDefault",
				 								                "parceiroNegocio.enderecoCobrancaDefault", "observacao");
	
	}

	public void inserirInterface(ContabilidadeLinha model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setInterfaceId(broker.getSequenceNextValue("contabilidades_linhas_id_seq"));

		broker.setPropertySQL("contabilidadelinhadao.inserirInterface", model.getInterfaceId(), model.getNumero(), model.getContabilidade().getInterfaceId(), model.getValorCredito(), model.getValorDebito(),
																		model.getDataVencimento(), model.getDataLancamento(), model.getCodigoProjeto(), model.getReferencia1(), model.getReferencia2(), 
																		Utilitarios.tratarString(model.getContaContabil().getId()), Utilitarios.tratarString(model.getCentroCusto().getId()),
																		model.getParceiroNegocio().getId(), model.getParceiroNegocio().getIdentificadorFiscal().getTipoIdentificador(), 
																		model.getParceiroNegocio().getIdentificadorFiscal().getIdentificador(), model.getParceiroNegocio().getNome(), 
																		model.getParceiroNegocio().getNomeFantasia(), model.getParceiroNegocio().getTelefoneResidencial(), 
																		model.getParceiroNegocio().getTelefoneCelular(), model.getParceiroNegocio().getFax(), model.getParceiroNegocio().getEmail(), 
																		model.getParceiroNegocio().getObservacao(), model.getParceiroNegocio().getEndereco().getLogradouro(), 
																		model.getParceiroNegocio().getEndereco().getNumero(), model.getParceiroNegocio().getEndereco().getComplemento(), 
																		model.getParceiroNegocio().getEndereco().getBairro(), model.getParceiroNegocio().getEndereco().getCidade(), 
																		model.getParceiroNegocio().getEndereco().getEstado().getId(), model.getParceiroNegocio().getEndereco().getCep(), 
																		model.getParceiroNegocio().getEndereco().getPais().getId(), model.getParceiroNegocio().getEndereco().getMunicipio().getId(), 
																		model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadual(), 
																		model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), 
																		model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoMunicipal(), 
																		model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoINSS(), model.getParceiroNegocio().getDataAtualizacao(), 
																		model.getParceiroNegocio().getClassificacao().getId(), model.getParceiroNegocio().getTipo(), model.getParceiroNegocio().getFlagEndereco(),
																		model.getParceiroNegocio().getEnderecoEntregaDefault(), model.getParceiroNegocio().getEnderecoCobrancaDefault(), model.getObservacao());
		
		broker.execute();
		
	}

}
