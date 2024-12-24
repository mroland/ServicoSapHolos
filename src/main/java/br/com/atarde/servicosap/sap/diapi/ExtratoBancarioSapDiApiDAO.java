/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.sap.diapi;

import com.sap.smb.sbo.api.BankPages;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;

import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.ExtratoBancario;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class ExtratoBancarioSapDiApiDAO {

	private ConexaoSapUtil conexaoSapUtil;
	private SBOErrorMessage errorMessage;
	private BankPages extrato;
	private Empresa empresa;
	private int retorno;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}

	}

	public void inserir(ExtratoBancario model) throws TSApplicationException {

		this.initObjetosNaRequisicao(model.getEmpresa());
		
		this.extrato = new BankPages(conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oBankPages));
		
		this.extrato.setAccountCode(model.getContaContabil().getId());
		
		if(!TSUtil.isEmpty(model.getValorCredito())){
			
			this.extrato.setCreditAmount(model.getValorCredito().doubleValue());
			
		}else{
			
			if(!TSUtil.isEmpty(model.getValorDebito())){
				
				this.extrato.setDebitAmount(model.getValorDebito().doubleValue());
				
			}
		}
		
		this.extrato.setReference(model.getCodigoInterno().getId());
		
		this.extrato.setDueDate(model.getDataVencimento());
		
		this.extrato.setMemo(model.getDetalhe()); //detalhe
		
		if(!TSUtil.isEmpty(model.getConta())){
			
			this.extrato.setExternalCode(model.getConta()); //conta
			
		}
		
		if(!TSUtil.isEmpty(model.getNumeroDocumento())){
			
			this.extrato.setInvoiceNumberEx(model.getNumeroDocumento());//numero do documento
			
		}
			
		this.retorno = this.extrato.add();
		
		if (this.retorno != 0) {

			this.errorMessage = conexaoSapUtil.getCompany().getLastError();

			model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

			throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

		} else {

			//model.setId(new Long(conexaoSapUtil.getCompany().getNewObjectCode().toString()));
			
			//System.out.println(conexaoSapUtil.getCompany().getNewObjectCode().toString());

		}
		
		
		this.extrato.release();

		this.extrato = null;

		//Runtime r = Runtime.getRuntime();

		//r.gc();

	}

}
