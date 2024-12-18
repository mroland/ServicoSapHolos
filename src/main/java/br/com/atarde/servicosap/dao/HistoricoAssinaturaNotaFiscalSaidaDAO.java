/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaidaParcela;
import br.com.atarde.servicosap.model.HistoricoAssinaturaNotaFiscalSaida;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.atarde.servicosap.sap.model.ParcelaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 *
 * @author mroland
 */
public class HistoricoAssinaturaNotaFiscalSaidaDAO {

    public HistoricoAssinaturaNotaFiscalSaidaDAO() {
    }

	public HistoricoAssinaturaNotaFiscalSaida inserirInterface(HistoricoAssinaturaNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("historico_assinaturanotafiscalsaida_id_seq"));

		broker.setPropertySQL("historicoassinaturanotafiscalsaidadao.inserirInterface", model.getInterfaceId(), model.getCliente().getId(), model.getCliente().getTipo(), 
				model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(), 
				model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), 
				model.getCliente().getTelefoneCelular(), model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), 
				model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), 
				model.getCliente().getEndereco().getBairro(), model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getEstado().getId(), 
				model.getCliente().getEndereco().getCep(), model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(), 
				model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), 
				model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(), 
				model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(), model.getVendedor().getId(), 
				model.getVendedor().getTipoIdentificador(), model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), 
				model.getVendedor().getGrupoComissao().getId(), model.getVendedor().getUCga(), model.getId(), model.getDataLancamento(), model.getDataDocumento(), 
				model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getDataExportacao(), model.getDataImportacao(), model.getDataAtualizacao(), 
				model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(), model.getIdExterno(), model.getEmpresa().getId(), 
				model.getOrigem().getId(), model.getUValorBruto(), model.getValor(), model.getUEnderecoEntrega(), model.getObservacao(), model.getUObservacao(), model.getPedidoVenda().getId(), 
				model.getCliente().getFlagEndereco(), model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(),
				model.getCliente().getEndereco().getTipoLogradouro());
		
		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			AssinaturaNotaFiscalSaidaLinha linha = (AssinaturaNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new AssinaturaNotaFiscalSaida("interfaceId",model.getInterfaceId()));
			
			new HistoricoAssinaturaNotaFiscalSaidaLinhaDAO().inserirInterface(linha,broker);

		}
		
		if(!TSUtil.isEmpty(model.getParcelas())){
			
			for (ParcelaAB p : model.getParcelas()) {
				
				AssinaturaNotaFiscalSaidaParcela parcela = (AssinaturaNotaFiscalSaidaParcela) p;
				
				parcela.setNotaFiscalSaida(new AssinaturaNotaFiscalSaida("interfaceId",model.getInterfaceId()));
				
				new HistoricoAssinaturaNotaFiscalSaidaParcelaDAO().inserirInterface(parcela,broker);
				
			}
			
		}

		broker.endTransaction();

		return model;
		
	}

}
