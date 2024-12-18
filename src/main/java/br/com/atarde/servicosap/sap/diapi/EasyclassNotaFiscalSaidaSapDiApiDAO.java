/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.sap.diapi;

import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

import com.sap.smb.sbo.api.Documents;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;

/**
 * 
 * @author mroland
 */
public class EasyclassNotaFiscalSaidaSapDiApiDAO {

	private ConexaoSapUtil conexaoSapUtil;
	private SBOErrorMessage errorMessage;
	private Documents documents;
	private Empresa empresa;
	private int retorno;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}

	}

	public void inserir(EasyclassNotaFiscalSaida model) throws TSApplicationException {

		this.initObjetosNaRequisicao(model.getEmpresa());

		this.documents = new Documents(conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_Document_oInvoices));

		this.documents.setCardCode(model.getCliente().getId());

		this.documents.setNumAtCard(model.getIdExterno());

		this.documents.setTaxDate(model.getDataDocumento());

		if (!TSUtil.isEmpty(model.getCondicaoPagamento().getId())) {
			// pegar a condicao de pagamento
			this.documents.setPaymentGroupCode(Integer.valueOf(model.getCondicaoPagamento().getId().toString()));
		} else {
			this.documents.setDocDueDate(model.getDataVencimento());
		}

		this.documents.setDocDate(model.getDataLancamento());

		this.documents.setSalesPersonCode(model.getVendedor().getId().intValue());

		if (!TSUtil.isEmpty(model.getCliente().getEnderecoEntregaDefault())) {

			this.documents.setShipToCode(model.getCliente().getEnderecoEntregaDefault());

		}

		if (!TSUtil.isEmpty(model.getCliente().getEnderecoCobrancaDefault())) {

			this.documents.setPayToCode(model.getCliente().getEnderecoCobrancaDefault());

		}

		this.documents.getUserFields().getFields().item("U_Origem").setValue(Integer.valueOf(model.getOrigem().getId().toString()));

		this.documents.getUserFields().getFields().item("U_EnderecoEntrega").setValue(model.getUEnderecoEntrega());

		if (!TSUtil.isEmpty(model.getUNumeroPI())) {

			this.documents.getUserFields().getFields().item("U_NumeroPI").setValue(model.getUNumeroPI());

		}

		this.documents.setSequenceCode(Integer.valueOf(model.getSequencia().getId().toString()));

		this.documents.getUserFields().getFields().item("U_ComissaoAgencia").setValue(model.getUComissaoAgencia().doubleValue());

		this.documents.getUserFields().getFields().item("U_DtPublicacaoFinal").setValue(TSParseUtil.dateToString(model.getUDataPublicacaoFinal(), TSDateUtil.YYYY_MM_DD));

		this.documents.getUserFields().getFields().item("U_DiasPublicacao").setValue(model.getUDiasPublicacao());

		this.documents.getUserFields().getFields().item("U_ValorBruto").setValue(model.getUValorBruto().doubleValue());

		this.documents.getUserFields().getFields().item("U_TituloPublicacao").setValue(model.getUTituloPublicacao());

		if (!TSUtil.isEmpty(model.getUAutorizacaoPublicidade())) {

			this.documents.getUserFields().getFields().item("U_AutPublicidade").setValue(model.getUAutorizacaoPublicidade());

		}

		if (!TSUtil.isEmpty(model.getUTipoTransacao())) {

			this.documents.getUserFields().getFields().item("U_TipoTransacao").setValue(model.getUTipoTransacao());

		}

		this.documents.getUserFields().getFields().item("U_AnuncianteId").setValue(model.getAnunciante().getId());

		if (model.getAnunciante().getFlagEndereco()) {

			if (!TSUtil.isEmpty(model.getAnunciante().getEndereco().getLogradouro())) {

				String endereco;

				endereco = model.getAnunciante().getEndereco().getLogradouro().concat(" ".concat(model.getAnunciante().getEndereco().getNumero()));

				if (!TSUtil.isEmpty(model.getAnunciante().getEndereco().getComplemento())) {

					endereco.concat(" ".concat(model.getAnunciante().getEndereco().getComplemento()));
				}

				if (!TSUtil.isEmpty(model.getAnunciante().getEndereco().getBairro())) {

					endereco.concat(" ".concat(model.getAnunciante().getEndereco().getBairro()));
				}

				endereco = TSStringUtil.rightPad(endereco, 254, " ").substring(0, 253).trim();

				this.documents.getUserFields().getFields().item("U_AnuncianteEndereco").setValue(endereco);

			}

			if (!TSUtil.isEmpty(model.getAnunciante().getEndereco().getCep())) {

				this.documents.getUserFields().getFields().item("U_AnuncianteCEP").setValue(model.getAnunciante().getEndereco().getCep());

			}

			if (!TSUtil.isEmpty(model.getAnunciante().getEndereco().getCidade())) {

				this.documents.getUserFields().getFields().item("U_AnuncianteCidade").setValue(model.getAnunciante().getEndereco().getCidade());

			}

			if (!TSUtil.isEmpty(model.getAnunciante().getEndereco().getEstado().getId())) {

				this.documents.getUserFields().getFields().item("U_AnuncianteEstado").setValue(model.getAnunciante().getEndereco().getEstado().getId());

			}

		}

		if (!TSUtil.isEmpty(model.getAnunciante().getIdentificadorFiscal().getIdentificador())) {

			this.documents.getUserFields().getFields().item("U_AnuncianteIdent").setValue(model.getAnunciante().getIdentificadorFiscal().getIdentificador());

		}

		if (!TSUtil.isEmpty(model.getAnunciante().getNome())) {

			this.documents.getUserFields().getFields().item("U_AnuncianteNome").setValue(model.getAnunciante().getNome());

		}

		if (!TSUtil.isEmpty(model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadual())) {

			this.documents.getUserFields().getFields().item("U_AnuncianteInscEst").setValue(model.getAnunciante().getIdentificadorFiscal().getInscricaoEstadual());

		}

		if (!TSUtil.isEmpty(model.getUPeriodo())) {

			this.documents.getUserFields().getFields().item("U_Periodo").setValue(model.getUPeriodo());

		}

		if (!TSUtil.isEmpty(model.getUPageViews())) {

			this.documents.getUserFields().getFields().item("U_PageViews").setValue(model.getUPageViews().doubleValue());

		}

		if (!TSUtil.isEmpty(model.getUFormato())) {

			this.documents.getUserFields().getFields().item("U_Formato").setValue(model.getUFormato());

		}
		
        if (!TSUtil.isEmpty(model.getUEntregaVendedor())) {

            documents.getUserFields().getFields().item("U_EntregaVendedor").setValue(model.getUEntregaVendedor());

        }    
        
        if (!TSUtil.isEmpty(model.getUProduto())) {

            documents.getUserFields().getFields().item("U_Produto").setValue(model.getUProduto());

        }   
        
        if (!TSUtil.isEmpty(model.getUCampanha())) {

            documents.getUserFields().getFields().item("U_Campanha").setValue(model.getUCampanha());

        }    
        
        if (!TSUtil.isEmpty(model.getUPostoId())) {

            documents.getUserFields().getFields().item("U_PostoId").setValue(model.getUPostoId());

        }  
        
        if(!TSUtil.isEmpty(model.getUDataPublicacaoInicial())){
            
            documents.getUserFields().getFields().item("U_DtPublicacaoIni").setValue(TSParseUtil.dateToString(model.getUDataPublicacaoInicial(), TSDateUtil.DD_MM_YYYY));
        
        }        
		
		// linhas do titulo
		int contador = 0;
		for (EasyclassNotaFiscalSaidaLinha linha : model.getLinhas()) {

			if (contador > 0) {

				this.documents.getLines().add();

			}

			this.documents.getLines().setItemCode(linha.getItem().getId());

			this.documents.getLines().setQuantity(linha.getQuantidade());

			this.documents.getLines().setLineTotal(linha.getValor().doubleValue());

			//this.documents.getLines().setTaxCode(linha.getCodigoImposto().getId());
			
			this.documents.getLines().setTaxCode("5933-001"); //fixo
			
            documents.getLines().setUsage("19"); ///fixo

			// falta os outros Atributos de linha da Nota

			this.documents.getLines().getUserFields().getFields().item("U_Area").setValue(linha.getUArea().doubleValue());

			this.documents.getLines().getUserFields().getFields().item("U_CmXCol").setValue(linha.getUCmXCol());

			this.documents.getLines().getUserFields().getFields().item("U_QtdInsercoes").setValue(linha.getUQuantidadeInsercoes());

			this.documents.getLines().getUserFields().getFields().item("U_TotalCmXCol").setValue(linha.getUTotalCmXCol().doubleValue());

			this.documents.getLines().getUserFields().getFields().item("U_ValorUnitario").setValue(linha.getUValorUnitario().doubleValue());
			
/*			

			if (!TSUtil.isEmpty(linha.getCstCOFINS())) {

				this.documents.getLines().setCSTforCOFINS(linha.getCstCOFINS().getCodigo());

			}

			if (!TSUtil.isEmpty(linha.getCstICMS())) {

				this.documents.getLines().setCSTCode(linha.getCstICMS().getCodigo());

			}

			if (!TSUtil.isEmpty(linha.getCstIPI())) {

				this.documents.getLines().setCSTforIPI(linha.getCstIPI().getCodigo());

			}

			if (!TSUtil.isEmpty(linha.getCstPIS())) {

				this.documents.getLines().setCSTforPIS(linha.getCstPIS().getCodigo());

			}
*/
			contador++;

		}

		this.retorno = documents.add();

		if (this.retorno != 0) {

			this.errorMessage = conexaoSapUtil.getCompany().getLastError();

			model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

			throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

		} else {

			model.setId(new Long(conexaoSapUtil.getCompany().getNewObjectCode().toString()));

		}

		this.documents.release();

		this.documents = null;

		//Runtime r = Runtime.getRuntime();

		//r.gc();

	}

}
