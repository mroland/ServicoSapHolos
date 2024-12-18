/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.sap.diapi;

import java.util.Date;

import com.sap.smb.sbo.api.BankStatement;
import com.sap.smb.sbo.api.BankStatementRow;
import com.sap.smb.sbo.api.BankStatementsService;
import com.sap.smb.sbo.api.CompanyService;
import com.sap.smb.sbo.api.IBankStatementsService;
import com.sap.smb.sbo.api.ICompanyService;
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
public class ExtratoBancarioSap3DiApiDAO {

	private ConexaoSapUtil conexaoSapUtil;
	private SBOErrorMessage errorMessage;
	private BankStatement extrato;
	private BankStatementRow linha;
	private IBankStatementsService servico;
	private ICompanyService companyService;
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
		
		this.companyService = (CompanyService) conexaoSapUtil.getCompany().getCompanyService();
		
		this.servico = new BankStatementsService(companyService.getBusinessService(SBOCOMConstants.ServiceTypes_BankStatementsService));
		
		this.extrato = new BankStatement(servico.getDataInterface(SBOCOMConstants.BankStatementsServiceDataInterfaces_bssBankStatement));
		
		this.extrato.setBankAccountKey(3); //VendaAvulsa
		
		this.linha = (BankStatementRow) this.extrato.getBankStatementRows().add();		
		
		this.linha.setDueDate(new Date());
		
		this.linha.setDebitAmountLC(new Double(100));
		
		//this.linha.setCreditAmountLC(new Double(200));
		
		this.linha.setDetails("Teste linha");
		
		this.servico.addBankStatement(this.extrato);
		
		if (this.retorno != 0) {

			this.errorMessage = conexaoSapUtil.getCompany().getLastError();

			model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

			throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

		} else {

			//model.setId(new Long(conexaoSapUtil.getCompany().getNewObjectCode().toString()));

		}
		

		this.servico.release();
		
		this.servico = null;
		
		this.extrato.release();

		this.extrato = null;
		
		this.linha.release();
		
		this.linha = null;

		//Runtime r = Runtime.getRuntime();

		//r.gc();

	}

}
