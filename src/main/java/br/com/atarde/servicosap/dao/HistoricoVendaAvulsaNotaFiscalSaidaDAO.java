/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.HistoricoVendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 *
 * @author mroland
 */
public class HistoricoVendaAvulsaNotaFiscalSaidaDAO {

    public HistoricoVendaAvulsaNotaFiscalSaidaDAO() {
    }

	public HistoricoVendaAvulsaNotaFiscalSaida inserirInterface(HistoricoVendaAvulsaNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("historico_vendaavulsanotafiscalsaida_id_seq"));

		broker.setPropertySQL("historicovendaavulsanotafiscalsaidadao.inserirInterface", model.getInterfaceId(), model.getCliente().getId(), model.getCliente().getTipo(), 
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
				model.getOrigem().getId(), model.getUValorBruto(), model.getValor(), model.getUEnderecoEntrega(), model.getObservacao(), model.getUBanca(), 
				model.getULote(), model.getUTipoBanca(), model.getUTipoFaturamento(), model.getUObservacao(), model.getPedidoVenda().getId(), 
				model.getCliente().getFlagEndereco(), model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(),
				model.getCliente().getEndereco().getTipoLogradouro());
		
		broker.execute();

		for (NotaFiscalSaidaLinhaAB item : model.getLinhas()) {

			VendaAvulsaNotaFiscalSaidaLinha linha = (VendaAvulsaNotaFiscalSaidaLinha) item;

			linha.setNotaFiscalSaida(new VendaAvulsaNotaFiscalSaida("interfaceId",model.getInterfaceId()));
			
			new HistoricoVendaAvulsaNotaFiscalSaidaLinhaDAO().inserirInterface(linha,broker);

		}
		
		if (!TSUtil.isEmpty(model.getRomaneios()) && model.getRomaneios().size() != 0) {
			
			for (VendaAvulsaNotaFiscalSaidaRomaneio romaneio : model.getRomaneios()) {
				
				romaneio.setNota(new VendaAvulsaNotaFiscalSaida("interfaceId",model.getInterfaceId()));
				
				romaneio.getNota().setId(model.getId());
				
				romaneio.setEmpresa(model.getEmpresa());
				
				new HistoricoVendaAvulsaNotaFiscalSaidaRomaneioDAO().inserirInterface(romaneio, broker);

			}

		}		

		broker.endTransaction();

		return model;
		
	}

}
