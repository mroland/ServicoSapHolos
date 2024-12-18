package br.com.atarde.servicosap.sap.diapi;

import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

import com.sap.smb.sbo.api.Documents;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;

public class VendaAvulsaNotaFiscalSaidaSapDiApiDAO {

	private ConexaoSapUtil conexaoSapUtil;
	private SBOErrorMessage errorMessage;
    private Documents documents;
	private Empresa empresa;    
	private int retorno;   	

    public void inserir(VendaAvulsaNotaFiscalSaida model) throws TSApplicationException { 	
    	
		this.initObjetosNaRequisicao(model.getEmpresa());
		
		this.documents = new Documents(conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_Document_oInvoices));		

        this.documents.setCardCode(model.getCliente().getId());

        this.documents.setNumAtCard(model.getIdExterno());

        this.documents.setTaxDate(model.getDataDocumento());

        if (!TSUtil.isEmpty(model.getCondicaoPagamento().getId())) {
            //pegar a condicao de pagamento
            this.documents.setPaymentGroupCode(Integer.valueOf(model.getCondicaoPagamento().getId().toString()));
        } else {
            this.documents.setDocDueDate(model.getDataVencimento());
        }

        this.documents.setDocDate(model.getDataLancamento());

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

        documents.getUserFields().getFields().item("U_Banca").setValue(model.getUBanca());

        documents.getUserFields().getFields().item("U_Lote").setValue(model.getULote());

        documents.getUserFields().getFields().item("U_TipoBanca").setValue(model.getUTipoBanca());

        documents.getUserFields().getFields().item("U_TipoFaturamento").setValue(model.getUTipoFaturamento());

        documents.getUserFields().getFields().item("U_Observacao").setValue(model.getUObservacao());

        //linhas do titulo
        int contador = 0;
        for (VendaAvulsaNotaFiscalSaidaLinha linha : model.getLinhas()) {


            if (contador > 0) {
                documents.getLines().add();
            }

            documents.getLines().setItemCode(linha.getItem().getId());

            documents.getLines().setQuantity(linha.getQuantidade());

            documents.getLines().setLineTotal(linha.getValor().doubleValue());

/*
            documents.getLines().setTaxCode(linha.getCodigoImposto().getId());

            documents.getLines().setAccountCode(linha.getContaContabil().getId());
            
            documents.getLines().setCFOPCode(linha.getCfop().getCodigo());

            documents.getLines().setCSTCode(linha.getCstICMS().getCodigo());

            documents.getLines().setCSTforCOFINS(linha.getCstCOFINS().getCodigo());

            documents.getLines().setCSTforIPI(linha.getCstIPI().getCodigo());

            documents.getLines().setCSTforPIS(linha.getCstPIS().getCodigo());
            
  */       
            
            documents.getLines().setAccountCode(linha.getContaContabil().getId()); 
            
            documents.getLines().setUsage(linha.getUtilizacao().getId().toString());//fixo
            
            documents.getLines().setTaxCode(linha.getCodigoImposto().getId());//fixo
            

/*            
            if(model.getSequencia().getId().equals(26L)){
                
                documents.getLines().setUsage("16");//fixo

                documents.getLines().setTaxCode("5101-007");//fixo
                
            }else{
            	
                if(linha.getFlagImposto()){
                	
                    documents.getLines().setUsage("21");//fixo
                    
                }else{
                	
                    documents.getLines().setUsage("16");//fixo
                    
                }
                          
                documents.getLines().setTaxCode("5101-006");//fixo
                
            }
*/
            
            if(linha.getFlagImposto()){
            	
                documents.getLines().setTaxOnly(SBOCOMConstants.BoYesNoEnum_tYES);
            	
            }
            
            contador++;

        }

        retorno = documents.add();

        if (retorno != 0) {

            errorMessage = conexaoSapUtil.getCompany().getLastError();

            model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

            throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

        } else {
        	
        	model.setId(new Long(conexaoSapUtil.getCompany().getNewObjectCode().toString()));
        	
        	if(!TSUtil.isEmpty(model.getRomaneios()) && model.getRomaneios().size()>0){
        	
        		for (VendaAvulsaNotaFiscalSaidaRomaneio item : model.getRomaneios()) {
        			
        			if(TSUtil.isEmpty(item.getNota())){
        				
        				item.setNota(new VendaAvulsaNotaFiscalSaida());
        				
        			}
					
        			item.getNota().setId(model.getId());
        			
				}
        	}
        	
            //System.out.println("Nota cadastrada com Sucesso!!, uHuuuuuu");
            
        }

        this.documents.release();

        this.documents = null;

       // Runtime r = Runtime.getRuntime();

       // r.gc();

    }

    private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}
		
    }

}
