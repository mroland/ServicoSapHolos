package br.com.atarde.servicosap.dao;

import br.com.atarde.servicosap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosap.model.DevolucaoNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class DevolucaoNotaFiscalSaidaDAO {

	public DevolucaoNotaFiscalSaida obterIdExternoInterface(DevolucaoNotaFiscalSaida model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setSQL("SELECT ID FROM DEVOLUCAO_NOTAFISCALSAIDA WHERE ID_EXTERNO = ? AND EMPRESA_ID = ?", model.getIdExterno(), model.getEmpresa().getId());

		return (DevolucaoNotaFiscalSaida) broker.getObjectBean(DevolucaoNotaFiscalSaida.class, "interfaceId");
	}

	public DevolucaoNotaFiscalSaida inserirInterface(DevolucaoNotaFiscalSaida model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setInterfaceId(broker.getSequenceNextValue("devolucao_notafiscalsaida_id_seq"));

		broker.setSQL("INSERT INTO DEVOLUCAO_NOTAFISCALSAIDA(ID, CLIENTE_ID, CLIENTE_TIPO, CLIENTE_TIPO_IDENTIFICADOR, CLIENTE_IDENTIFICADOR, CLIENTE_NOME, CLIENTE_NOME_FANTASIA, CLIENTE_TELEFONE_RESIDENCIAL, CLIENTE_TELEFONE_CELULAR, CLIENTE_FAX, CLIENTE_EMAIL, CLIENTE_OBSERVACAO, CLIENTE_ENDERECO_LOGRADOURO, CLIENTE_ENDERECO_NUMERO, CLIENTE_ENDERECO_COMPLEMENTO, CLIENTE_ENDERECO_BAIRRO, CLIENTE_ENDERECO_CIDADE, CLIENTE_ENDERECO_ESTADO, CLIENTE_ENDERECO_CEP, CLIENTE_ENDERECO_PAIS, CLIENTE_ENDERECO_MUNICIPIO, CLIENTE_INSCRICAO_ESTADUAL, CLIENTE_INSCRICAO_ESTADUAL_SUBTRIB, CLIENTE_INSCRICAO_MUNICIPAL, CLIENTE_INSCRICAO_INSS, CLIENTE_DATA_ATUALIZACAO, CLIENTE_CLASSIFICACAO_ID, VENDEDOR_ID, VENDEDOR_TIPO_IDENTIFICADOR, VENDEDOR_IDENTIFICADOR, VENDEDOR_NOME, VENDEDOR_DATA_ATUALIZACAO, VENDEDOR_GRUPO_COMISSAO_ID, VENDEDOR_U_CGA, SAP_DEVOLUCAO_NOTAFISCALSAIDA_ID, DATA_LANCAMENTO, DATA_DOCUMENTO, DATA_VENCIMENTO, CONDICAO_PAGAMENTO_ID, DATA_EXPORTACAO, DATA_IMPORTACAO, DATA_ATUALIZACAO, SEQUENCIA_ID, STATUS_ID, MENSAGEM_ERRO, ID_EXTERNO, EMPRESA_ID, ORIGEM_ID, VALOR, OBSERVACAO, CLIENTE_FLAG_ENDERECO,CLIENTE_ENDERECO_ENTREGA_DEFAULT, CLIENTE_ENDERECO_COBRANCA_DEFAULT, CLIENTE_ENDERECO_TIPO_LOGRADOURO, FILIAL_ID, FLAG_CONSIGNADO, ARQUIVO_REMESSA) VALUES(?,?,?,?,?,UPPER(?),UPPER(?),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", model.getInterfaceId(), model.getCliente().getId(), model.getCliente().getTipo(), model.getCliente().getIdentificadorFiscal().getTipoIdentificador(), model.getCliente().getIdentificadorFiscal().getIdentificador(), model.getCliente().getNome(), model.getCliente().getNomeFantasia(), model.getCliente().getTelefoneResidencial(), model.getCliente().getTelefoneCelular(), model.getCliente().getFax(), model.getCliente().getEmail(), model.getCliente().getObservacao(), model.getCliente().getEndereco().getLogradouro(), model.getCliente().getEndereco().getNumero(), model.getCliente().getEndereco().getComplemento(), model.getCliente().getEndereco().getBairro(), model.getCliente().getEndereco().getCidade(), model.getCliente().getEndereco().getEstado().getId(), model.getCliente().getEndereco().getCep(), model.getCliente().getEndereco().getPais().getId(), model.getCliente().getEndereco().getMunicipio().getId(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadual(), model.getCliente().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria(), model.getCliente().getIdentificadorFiscal().getInscricaoMunicipal(), model.getCliente().getIdentificadorFiscal().getInscricaoINSS(), model.getCliente().getDataAtualizacao(), model.getCliente().getClassificacao().getId(), model.getVendedor().getId(), model.getVendedor().getTipoIdentificador(), model.getVendedor().getIdentificador(), model.getVendedor().getNome(), model.getVendedor().getDataAtualizacao(), model.getVendedor().getGrupoComissao().getId(), model.getVendedor().getUCga(), model.getId(), model.getDataLancamento(), model.getDataDocumento(), model.getDataVencimento(), model.getCondicaoPagamento().getId(), model.getDataExportacao(), model.getDataImportacao(), model.getDataAtualizacao(), model.getSequencia().getId(), model.getStatus().getId(), model.getMensagemErro(), model.getIdExterno(), model.getEmpresa().getId(), model.getOrigem().getId(), model.getValor(), model.getObservacao(), model.getCliente().getFlagEndereco(), model.getCliente().getEnderecoEntregaDefault(), model.getCliente().getEnderecoCobrancaDefault(), model.getCliente().getEndereco().getTipoLogradouro(), model.getFilial().getId(), model.getFlagConsignado(), model.getArquivoRemessa());

		broker.execute();

		for (DevolucaoNotaFiscalSaidaLinha item : model.getLinhas()) {

			item.setDevolucaoNotaFiscalSaida(new DevolucaoNotaFiscalSaida("interfaceId", model.getInterfaceId()));

			new DevolucaoNotaFiscalSaidaLinhaDAO().inserirInterface(item, broker);

		}

		if (!TSUtil.isEmpty(model.getTransferenciaEstoqueReferencia())) {

			model.getTransferenciaEstoqueReferencia().setDevolucaoNotaFiscalSaidaReferenciada(new DevolucaoNotaFiscalSaida(model.getInterfaceId()));

			new TransferenciaEstoqueDAO().inserirInterface(model.getTransferenciaEstoqueReferencia(), broker);

		}

		broker.endTransaction();

		return model;
	}

	public DevolucaoNotaFiscalSaida obterIdExterno(NotaFiscalSaidaAB model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT ORIN.\"DocEntry\" FROM " + model.getEmpresa().getDbInstancia() + ".ORIN WHERE ORIN.\"NumAtCard\" = ? AND ORIN.\"U_ATRD_Origem\"  = ? AND NOT EXISTS (SELECT 1 FROM " + model.getEmpresa().getDbInstancia() + ".ORIN C, " + model.getEmpresa().getDbInstancia() + ".INV1 L WHERE C.\"DocEntry\" = L.\"DocEntry\" AND L.\"BaseEntry\" = ORIN.\"DocEntry\" AND C.CANCELED = 'Y') AND ORIN.CANCELED ='N' ", model.getIdExterno(), model.getOrigem().getId());

		return (DevolucaoNotaFiscalSaida) broker.getObjectBean(DevolucaoNotaFiscalSaida.class, "id");
		
	}

}
