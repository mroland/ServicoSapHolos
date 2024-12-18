/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.sap.diapi;

import java.util.Date;

import com.sap.smb.sbo.api.CompanyService;
import com.sap.smb.sbo.api.ICompanyService;
import com.sap.smb.sbo.api.IProfitCenter;
import com.sap.smb.sbo.api.IProfitCenterParams;
import com.sap.smb.sbo.api.IProfitCentersService;
import com.sap.smb.sbo.api.ProfitCenter;
import com.sap.smb.sbo.api.ProfitCenterParams;
import com.sap.smb.sbo.api.ProfitCentersService;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;

import br.com.atarde.servicosap.sap.model.CentroCusto;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class CentroCustoDiApiDAO {

	private ConexaoSapUtil conexaoSapUtil;
	private SBOErrorMessage errorMessage;
	private IProfitCenter centroCusto;
	private IProfitCenterParams parametro;
	private IProfitCentersService servico;
	private ICompanyService companyService;
	private Empresa empresa;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}

	}

	public void inserir(CentroCusto model) throws TSApplicationException {

		this.initObjetosNaRequisicao(model.getEmpresa());
		
		this.companyService = (CompanyService) conexaoSapUtil.getCompany().getCompanyService();
		
		this.servico = new ProfitCentersService(companyService.getBusinessService(SBOCOMConstants.ServiceTypes_ProfitCentersService));
		
		this.parametro = new ProfitCenterParams(companyService.getDataInterface(SBOCOMConstants.DimensionsServiceDataInterfaces_dsDimensionParams));
		
		this.centroCusto = new ProfitCenter(servico.getDataInterface(SBOCOMConstants.ProfitCentersServiceDataInterfaces_pcsProfitCenter));
		
		this.centroCusto.setActive(SBOCOMConstants.BoYesNoEnum_tYES);
		
		this.centroCusto.setCenterCode("xxx");
		
		this.centroCusto.setCenterName("Teste1");
		
		this.centroCusto.setCostCenterType("CC2015");
		
		this.centroCusto.setInWhichDimension(1);
		
		this.centroCusto.setEffectivefrom(new Date());
		
		this.parametro = this.servico.addProfitCenter(centroCusto);
		
		System.out.println(this.parametro.getCenterCode());
		
		if (TSUtil.isEmpty(this.parametro.getCenterCode())) {

			this.errorMessage = conexaoSapUtil.getCompany().getLastError();

			model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

			throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

		} else {

			//model.setId(new Long(conexaoSapUtil.getCompany().getNewObjectCode().toString()));

		}
		

		this.servico.release();
		
		this.servico = null;
		
		this.centroCusto.release();

		this.centroCusto = null;

		//Runtime r = Runtime.getRuntime();

		//r.gc();

	}


}
