/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.dao;

import java.util.List;

import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;


/**
 * 
 * @author mroland
 */
public class EasyclassNotaFiscalSaidaDAO {

	public EasyclassNotaFiscalSaida inserirInterface(EasyclassNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("easyclassnotafiscalsaida_id_seq"));

		broker.setPropertySQL("easyclassnotafiscalsaidadao.inserirInterface", model.getInterfaceId(),
				
				model.getCliente().getId(), model.getCliente().getTipo(), 
				model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(), 
				model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), 
				model.getCliente().getTelefoneCelular(), model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), 
				model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), 
				model.getCliente().getEndereco().getBairro(), model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getEstado().getId(), 
				model.getCliente().getEndereco().getCep(), model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(), 
				model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), 
				model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(), 
				model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(), 
				
				model.getAnunciante().getId(), model.getAnunciante().getTipo(), 
				model.getAnunciante().getIdentificadorFiscal().getTipoIdentificador(), model.getAnunciante().getIdentificadorFiscal().getIdentificador(), 
				model.getAnunciante().getNome(), model.getAnunciante().getNomeFantasia(), model.getAnunciante().getTelefoneResidencial(), 
				model.getAnunciante().getTelefoneCelular(), model.getAnunciante().getFax(), model.getAnunciante().getEmail(), model.getAnunciante().getObservacao(), 
				model.getAnunciante().getEndereco().getLogradouro(), model.getAnunciante().getEndereco().getNumero(), model.getAnunciante().getEndereco().getComplemento(), 
				model.getAnunciante().getEndereco().getBairro(), model.getAnunciante().getEndereco().getCidade(), model.getAnunciante().getEndereco().getEstado().getId(), 
				model.getAnunciante().getEndereco().getCep(), model.getAnunciante().getEndereco().getPais().getId(), model.getAnunciante().getEndereco().getMunicipio().getId(), 
				model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadual(), model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), 
				model.getAnunciante().getIdentificadorFiscal().getInscricaoMunicipal(), model.getAnunciante().getIdentificadorFiscal().getInscricaoINSS(), 
				model.getAnunciante().getDataAtualizacao(), model.getAnunciante().getClassificacao().getId(), 				
							
				model.getVendedor().getId(), 
				model.getVendedor().getTipoIdentificador(), model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), 
				model.getVendedor().getGrupoComissao().getId(), model.getVendedor().getUCga(), model.getId(), model.getDataLancamento(), model.getDataDocumento(), 
				model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getDataExportacao(), model.getDataImportacao(), model.getDataAtualizacao(), 
				model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(), model.getIdExterno(), model.getEmpresa().getId(), 
				model.getOrigem().getId(), model.getUValorBruto(), model.getValor(), model.getUEnderecoEntrega(), model.getUComissaoAgencia(), model.getUDiasPublicacao(),
				model.getUDataPublicacaoFinal(), model.getUTituloPublicacao(), model.getUNumeroPI(), model.getUTipoTransacao(), model.getUPeriodo(), model.getUFormato(),
				model.getUPageViews(), model.getUEntregaVendedor(), model.getUProduto(), model.getUCampanha(), model.getUPostoId(), model.getUDataPublicacaoInicial(),		
				model.getPedidoVenda().getId(),
				
				model.getCliente().getFlagEndereco(), model.getAnunciante().getFlagEndereco(),
				
				model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(),
				
				model.getAnunciante().getEnderecoEntregaDefault(), model.getAnunciante().getEnderecoCobrancaDefault(),
				
				model.getCliente().getEndereco().getTipoLogradouro(), model.getAnunciante().getEndereco().getTipoLogradouro());
		
		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			EasyclassNotaFiscalSaidaLinha linha = (EasyclassNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new EasyclassNotaFiscalSaida("interfaceId",model.getInterfaceId()));
			
			new EasyclassNotaFiscalSaidaLinhaDAO().inserirInterface(linha,broker);

		}

		broker.endTransaction();

		return model;
	}

	@SuppressWarnings("unchecked")
	public List<EasyclassNotaFiscalSaida> pesquisarInterface(EasyclassNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("easyclassnotafiscalsaidadao.pesquisarInterface", model.getEmpresa().getId());
		
		return broker.getCollectionBean(EasyclassNotaFiscalSaida.class, "interfaceId", 
																		  "cliente.id", "cliente.tipo", "cliente.identificadorFiscal.tipoIdentificador",
				                             							  "cliente.identificadorFiscal.identificador", "cliente.nome", "cliente.nomeFantasia", "cliente.telefoneResidencial",
				                             							  "cliente.telefoneCelular", "cliente.fax", "cliente.email", "cliente.observacao", "cliente.endereco.logradouro",
				                             							  "cliente.endereco.numero", "cliente.endereco.complemento", "cliente.endereco.bairro", "cliente.endereco.cidade",
				                             							  "cliente.endereco.estado.id", "cliente.endereco.cep", "cliente.endereco.pais.id", "cliente.endereco.municipio.id",
				                             							  "cliente.identificadorFiscal.inscricaoEstadual", "cliente.identificadorFiscal.inscricaoEstadualSubstitutoTributaria",
				                             							  "cliente.identificadorFiscal.inscricaoMunicipal", "cliente.identificadorFiscal.inscricaoINSS", "cliente.dataAtualizacao",
				                             							  "cliente.classificacao.id",
				                             							  
																		  "anunciante.id", "anunciante.tipo", "anunciante.identificadorFiscal.tipoIdentificador",
				                             							  "anunciante.identificadorFiscal.identificador", "anunciante.nome", "anunciante.nomeFantasia", "anunciante.telefoneResidencial",
				                             							  "anunciante.telefoneCelular", "anunciante.fax", "anunciante.email", "anunciante.observacao", "anunciante.endereco.logradouro",
				                             							  "anunciante.endereco.numero", "anunciante.endereco.complemento", "anunciante.endereco.bairro", "anunciante.endereco.cidade",
				                             							  "anunciante.endereco.estado.id", "anunciante.endereco.cep", "anunciante.endereco.pais.id", "anunciante.endereco.municipio.id",
				                             							  "anunciante.identificadorFiscal.inscricaoEstadual", "anunciante.identificadorFiscal.inscricaoEstadualSubstitutoTributaria",
				                             							  "anunciante.identificadorFiscal.inscricaoMunicipal", "anunciante.identificadorFiscal.inscricaoINSS", "anunciante.dataAtualizacao",
				                             							  "anunciante.classificacao.id",
				                             							  
				                             							  "vendedor.id", "vendedor.tipoIdentificador", "vendedor.identificador", "vendedor.nome",
				                             							  "vendedor.dataAtualizacao", "vendedor.grupoComissao.id", "vendedor.uCga", 
				                             							  
				                             							  "id", "dataLancamento", "dataDocumento",
				                             							  "dataVencimento", "condicaoPagamento.id", "dataExportacao", "dataImportacao", "dataAtualizacao", "sequencia.id",
				                             							  "status.id", "mensagemErro", "idExterno", "empresa.id", "origem.id", "uValorBruto", "valor", "uEnderecoEntrega",
				                             							  "uComissaoAgencia", "uDiasPublicacao", "uDataPublicacaoFinal", "uTituloPublicacao", "uNumeroPI", "uTipoTransacao", 
				                             							  "uPeriodo", "uFormato", "uPageViews", "uEntregaVendedor", "uProduto", "uCampanha", "uPostoId", "uDataPublicacaoInicial",
				                             							  "pedidoVenda.id",
				                             							  "cliente.flagEndereco", "anunciante.flagEndereco", "cliente.enderecoEntregaDefault", "cliente.enderecoCobrancaDefault",
 				                             							  "anunciante.enderecoEntregaDefault", "anunciante.enderecoCobrancaDefault", "cliente.endereco.tipoLogradouro", "anunciante.endereco.tipoLogradouro",
 				                             							  "uAutorizacaoPublicidade");
	}

	public void alterarInterface(EasyclassNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("easyclassnotafiscalsaidadao.alterarInterface", model.getStatus().getId(), model.getMensagemErro(), model.getDataAtualizacao(), model.getDataImportacao(), model.getInterfaceId());
		
		broker.execute();
		
	}

	public void excluirInterface(EasyclassNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("easyclassnotafiscalsaidadao.excluirInterface", model.getInterfaceId());
		
		broker.execute();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<EasyclassNotaFiscalSaida> pesquisarPorAtrasadaInterface(EasyclassNotaFiscalSaida model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
        
        broker.setPropertySQL("easyclassnotafiscalsaidadao.pesquisarPorAtrasadaInterface", model.getStatus().getId());
        
        return broker.getCollectionBean(EasyclassNotaFiscalSaida.class, "interfaceId");
        
	}

	
	public EasyclassNotaFiscalSaida obterIdExternoInterface(EasyclassNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("easyclassnotafiscalsaidadao.obterIdExternoInterface", model.getIdExterno(), model.getEmpresa().getId());
		
		return (EasyclassNotaFiscalSaida) broker.getObjectBean(EasyclassNotaFiscalSaida.class, "interfaceId");
	}	
}
