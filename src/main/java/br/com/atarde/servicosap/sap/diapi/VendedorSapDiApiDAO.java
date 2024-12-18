/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.sap.diapi;

import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.Vendedor;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.atarde.servicosap.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;
import com.sap.smb.sbo.api.SalesPersons;

/**
 *
 * @author mroland
 */
public class VendedorSapDiApiDAO {

    private ConexaoSapUtil conexaoSapUtil;
    private SalesPersons vendedor;
    private SBOErrorMessage errorMessage;
    private int retorno;
	private Empresa empresa;        

    public Vendedor inserir(Vendedor model) throws TSApplicationException {

        this.initObjetosNaRequisicao(model.getEmpresa());

        this.vendedor = new SalesPersons(this.conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oSalesPersons));
        
        this.vendedor.setRemarks(model.getIdentificador());

        this.vendedor.setSalesEmployeeName(model.getNome().toUpperCase());

        this.vendedor.setCommissionGroup(Integer.valueOf(model.getGrupoComissao().getId().toString()));
        
        this.vendedor.getUserFields().getFields().item("U_Agrupador").setValue(model.getIdentificador());
        
        if(!TSUtil.isEmpty(model.getUCga())){
            
            this.vendedor.getUserFields().getFields().item("U_Cga").setValue(model.getUCga());
            
        }
                      
        if(model.getGrupoComissao().getId().equals(Constantes.GRUPO_COMISSAO_AGENCIA)){
        	
        	this.vendedor.setCommissionForSalesEmployee(20D);
        	
        }else{
            if(model.getGrupoComissao().getId().equals(Constantes.GRUPO_COMISSAO_CORRETORES)){
            	
            	this.vendedor.setCommissionForSalesEmployee(15D);
            	
            }else{
            	
            	if(model.getGrupoComissao().getId().equals(Constantes.GRUPO_COMISSAO_SVG)){
            		
            		this.vendedor.setCommissionForSalesEmployee(6D);
            		
            	}
            }
        	
        }
           

        retorno = this.vendedor.add();

        if (retorno != 0) {

            errorMessage = conexaoSapUtil.getCompany().getLastError();

            System.err.println("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

            model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

            throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

        } else {
            
            model.setId(new Long(conexaoSapUtil.getCompany().getNewObjectCode().toString()));

            //System.out.println("Vendedor cadastrado com sucesso. Tempo-->" + x.toString());

        }

        this.vendedor.release();

        this.vendedor = null;

        Runtime r = Runtime.getRuntime();

        r.gc();

        return model;

    }
    
    private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}
		
    }    
}
