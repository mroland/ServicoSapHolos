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

		broker.setSQL("INSERT INTO HISTORICO_VENDAAVULSANOTAFISCALSAIDA(ID, CLIENTE_ID, CLIENTE_TIPO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, VENDEDOR_U_CGA, SAP_NOTAFISCALSAIDA_ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, ID_EXTERNO, EMPRESA_ID, ORIGEM_ID, U_VALOR_BRUTO, VALOR, U_ENDERECO_ENTREGA, OBSERVACAO, U_BANCA, U_LOTE, U_TIPO_BANCA, U_TIPO_FATURAMENTO, U_OBSERVACAO, PEDIDO_VENDA_ID, CLIENTE_FLAG_ENDERECO,CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_TIPO_LOGRADOURO, FILIAL_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getCliente().getId(), model.getCliente().getTipo(), 
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
				model.getCliente().getEndereco().getTipoLogradouro(), model.getFilial().getId());
		
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
