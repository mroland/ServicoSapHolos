package br.com.atarde.servicosap.sap.diapi;

import com.sap.smb.sbo.api.Documents;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;

import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.ParcelaAB;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class AssinaturaNotaFiscalSaidaSapDiApiDAO {

	private ConexaoSapUtil conexaoSapUtil;
	private SBOErrorMessage errorMessage;
    private Documents documents;
	private Empresa empresa;    

    public void inserir(AssinaturaNotaFiscalSaida model) throws TSApplicationException {
    	
		int retorno;    	
    	
		this.initObjetosNaRequisicao(model.getEmpresa());
		
		this.documents = new Documents(conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_Document_oInvoices));		

        this.documents.setCardCode(model.getCliente().getId());

        this.documents.setNumAtCard(model.getIdExterno());

        this.documents.setTaxDate(model.getDataDocumento());

        if (!TSUtil.isEmpty(model.getCondicaoPagamento().getId())) {
            //pegar a condicao de pagamento
            this.documents.setPaymentGroupCode(Integer.valueOf(model.getCondicaoPagamento().getId().toString()));
            
            if(!TSUtil.isEmpty(model.getParcelas())){
            	
            	int contador = 0;
            	
                for (ParcelaAB parcela : model.getParcelas()) {
                	
                	if(contador>0){
                		
                		this.documents.getInstallments().add();
                		
                	}
    				
                	this.documents.getInstallments().setDueDate(parcela.getDataVencimento());
                	
                	this.documents.getInstallments().setTotal(parcela.getValor().doubleValue());
                	
                	contador++;
                	
    			}
                
            }
            
        } else {
            this.documents.setDocDueDate(model.getDataVencimento());
        }

        this.documents.setDocDate(model.getDataLancamento());

        //this.documents.setSalesPersonCode(Integer.valueOf(new VendedorDAO().obterPeloIdentificador(model.getVendedor()).getId().toString()));
        
        this.documents.setSalesPersonCode(model.getVendedor().getId().intValue());
        
        if(!TSUtil.isEmpty(model.getCliente().getEnderecoEntregaDefault())){
        	
            this.documents.setShipToCode(model.getCliente().getEnderecoEntregaDefault());
        	
        }
        
        if(!TSUtil.isEmpty(model.getCliente().getEnderecoCobrancaDefault())){
        	
            this.documents.setPayToCode(model.getCliente().getEnderecoCobrancaDefault());        	
        	
        }

        //documents.setShipToCode(new ParceiroNegocioEnderecoDAO().obterPeloTipoEndereco(new ParceiroNegocioEndereco(model.getClienteCobranca(), "tipoEndereco", "S")).getId());

        //documents.setPayToCode(new ParceiroNegocioEnderecoDAO().obterPeloTipoEndereco(new ParceiroNegocioEndereco(model.getClienteCobranca(), "tipoEndereco", "B")).getId());

        documents.getUserFields().getFields().item("U_Origem").setValue(Integer.valueOf(model.getOrigem().getId().toString()));

        documents.getUserFields().getFields().item("U_EnderecoEntrega").setValue(model.getUEnderecoEntrega());

        documents.setSequenceCode(Integer.valueOf(model.getSequencia().getId().toString()));

        documents.setComments(model.getObservacao());

        documents.getTaxExtension().setIncoterms("0");

        documents.getUserFields().getFields().item("U_Observacao").setValue(model.getUObservacao());
        
        if(!TSUtil.isEmpty(model.getUTermo())){
        	
            documents.getUserFields().getFields().item("U_Termo").setValue(model.getUTermo());
            
        }

        //linhas do titulo
        int contador = 0;
        for (AssinaturaNotaFiscalSaidaLinha linha : model.getLinhas()) {


            if (contador > 0) {
                documents.getLines().add();
            }

            documents.getLines().setItemCode(linha.getItem().getId());

            documents.getLines().setQuantity(linha.getQuantidade());

            documents.getLines().setLineTotal(linha.getValor().doubleValue());
            
            documents.getLines().setUsage(linha.getUtilizacao().getId().toString());//fixo
            
            documents.getLines().setTaxCode(linha.getCodigoImposto().getId());//fixo            
                        
/*          
            
            documents.getLines().setCFOPCode(linha.getCfop().getCodigo());

            documents.getLines().setCSTCode(linha.getCstICMS().getCodigo());

            documents.getLines().setCSTforCOFINS(linha.getCstCOFINS().getCodigo());

            documents.getLines().setCSTforIPI(linha.getCstIPI().getCodigo());

            documents.getLines().setCSTforPIS(linha.getCstPIS().getCodigo());
            
*/
            contador++;

        }

        retorno = documents.add();

        if (retorno != 0) {

            errorMessage = conexaoSapUtil.getCompany().getLastError();

            model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

            throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

        } else {
        	
        	model.setId(new Long(conexaoSapUtil.getCompany().getNewObjectCode().toString()));
        	
            //System.out.println("Nota cadastrada com Sucesso!!, uHuuuuuu");
            
        }

        this.documents.release();

        this.documents = null;

        //Runtime r = Runtime.getRuntime();

        //r.gc();

    }

    private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}
		
    }

	public SBOErrorMessage getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(SBOErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}
}
