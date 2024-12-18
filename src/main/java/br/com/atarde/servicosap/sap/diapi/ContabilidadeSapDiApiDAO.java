package br.com.atarde.servicosap.sap.diapi;

import br.com.atarde.servicosap.sap.model.Contabilidade;
import br.com.atarde.servicosap.sap.model.ContabilidadeLinha;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

import com.sap.smb.sbo.api.JournalEntries;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;

public class ContabilidadeSapDiApiDAO {

	
	private ConexaoSapUtil conexaoSapUtil;
	private SBOErrorMessage errorMessage;
	private JournalEntries oJournalEntries;
	private Empresa empresa;
	private int retorno;  	

    private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}
		
    }

	public void inserir(Contabilidade model) throws TSApplicationException {
		
		this.initObjetosNaRequisicao(model.getEmpresa());
		
		this.oJournalEntries = new JournalEntries(conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oJournalEntries));		

		this.oJournalEntries.setSeries(17);
		
		this.oJournalEntries.setDueDate(model.getDataVencimento());
		
		this.oJournalEntries.setReferenceDate(model.getDataLancamento());
		
		this.oJournalEntries.setTaxDate(model.getDataDocumento());
		
		if(!TSUtil.isEmpty(model.getObservacao())){
			
			this.oJournalEntries.setMemo(model.getObservacao());			
			
		}
		
		if(!TSUtil.isEmpty(model.getReferencia2())){
			
			this.oJournalEntries.setReference2(model.getReferencia2());
			
		}
		
        //linhas do titulo
        int contador = 0;
        for (ContabilidadeLinha linha : model.getLinhas()) {


            if (contador > 0) {
            	this.oJournalEntries.getLines().add();
            }

            this.oJournalEntries.getLines().setCredit(linha.getValorCredito().doubleValue());
            
            this.oJournalEntries.getLines().setDebit(linha.getValorDebito().doubleValue());
            
            if(!TSUtil.isEmpty(linha.getObservacao())){
            	
                this.oJournalEntries.getLines().setLineMemo(linha.getObservacao());        	
            	
            }              
            
            if(!TSUtil.isEmpty(linha.getDataVencimento())){
            	
            	this.oJournalEntries.getLines().setDueDate(linha.getDataVencimento());            	
            	
            }
            
            if(!TSUtil.isEmpty(linha.getDataLancamento())){
            	
                this.oJournalEntries.getLines().setReferenceDate1(linha.getDataLancamento());        	
            	
            }            

            if(!TSUtil.isEmpty(linha.getCodigoProjeto())){
            	
            	this.oJournalEntries.getLines().setProjectCode(linha.getCodigoProjeto());
            	
            }
            
            if(!TSUtil.isEmpty(linha.getReferencia1())){
            	
            	this.oJournalEntries.getLines().setReference1(linha.getReferencia1());
            	
            }
            
            if(!TSUtil.isEmpty(linha.getReferencia2())){
            	
            	this.oJournalEntries.getLines().setReference2(linha.getReferencia2());
            	
            }
            
            if(!TSUtil.isEmpty(linha.getContaContabil().getId())){
            	
            	this.oJournalEntries.getLines().setAccountCode(linha.getContaContabil().getId());
            	
            }
            
            if(!TSUtil.isEmpty(linha.getCentroCusto())){
            
            	this.oJournalEntries.getLines().setCostingCode(linha.getCentroCusto().getId());
            	
            }
            
            if(!TSUtil.isEmpty(linha.getParceiroNegocio()) && (!TSUtil.isEmpty(Utilitarios.tratarString(linha.getParceiroNegocio().getId())))){
                
                this.oJournalEntries.getLines().setShortName(linha.getParceiroNegocio().getId());
            	
            }
                                       
            contador++;

        }
        
        retorno = oJournalEntries.add();

        if (retorno != 0) {

            errorMessage = conexaoSapUtil.getCompany().getLastError();

            model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

            throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

        } else {
        	
        	model.setId(new Long(conexaoSapUtil.getCompany().getNewObjectCode().toString()));
        	
            //System.out.println("Nota cadastrada com Sucesso!!, uHuuuuuu");
            
        }

        this.oJournalEntries.release();

        this.oJournalEntries = null;

        //Runtime r = Runtime.getRuntime();

        //r.gc();


	}
	
}
