/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.model.HistoricoEasyclassNotaFiscalSaida;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;

/**
 *
 * @author mroland
 */
public class HistoricoEasyclassNotaFiscalSaidaDAO {

    public HistoricoEasyclassNotaFiscalSaidaDAO() {
    }

	public HistoricoEasyclassNotaFiscalSaida inserirInterface(HistoricoEasyclassNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("historico_easyclassnotafiscalsaida_id_seq"));

		broker.setPropertySQL("historicoeasyclassnotafiscalsaidadao.inserirInterface",  model.getInterfaceId(),
				
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
				
				model.getCliente().getFlagEndereco(), model.getAnunciante().getFlagEndereco(), model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(),
				model.getAnunciante().getEnderecoEntregaDefault(), model.getAnunciante().getEnderecoCobrancaDefault(),
				model.getCliente().getEndereco().getTipoLogradouro(), model.getAnunciante().getEndereco().getTipoLogradouro());
		
		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			EasyclassNotaFiscalSaidaLinha linha = (EasyclassNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new EasyclassNotaFiscalSaida("interfaceId",model.getInterfaceId()));
			
			new HistoricoEasyclassNotaFiscalSaidaLinhaDAO().inserirInterface(linha,broker);

		}
		
		broker.endTransaction();

		return model;
		
	}

}
