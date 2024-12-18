/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.sap.diapi;

import br.com.atarde.servicosap.sap.model.ParceiroNegocioContaBanco;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

import com.sap.smb.sbo.api.BusinessPartners;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;

/**
 *
 * @author mroland
 */
public class ParceiroNegocioContaBancoSapDiApiDAO {

    private ConexaoSapUtil conexaoSapUtil;
    private BusinessPartners cliente;
    private SBOErrorMessage errorMessage;
    private int retorno;

    public ParceiroNegocioContaBancoSapDiApiDAO() throws TSApplicationException {

        this.initObjetosNaRequisicao();

        this.cliente = new BusinessPartners(this.conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oBusinessPartners));

    }

    public ParceiroNegocioContaBanco inserir(ParceiroNegocioContaBanco model) throws TSApplicationException {

        if (this.cliente.getByKey(model.getParceiroNegocio().getId())) {

            if (!TSUtil.isEmpty(model.getBanco().getConta().getNumeroAgencia())) {

                this.cliente.getBPBankAccounts().setBranch(model.getBanco().getConta().getNumeroAgencia());

            }

            if (!TSUtil.isEmpty(model.getBanco().getCodigo())) {

                this.cliente.getBPBankAccounts().setBankCode(model.getBanco().getCodigo());

            }

            if (!TSUtil.isEmpty(model.getBanco().getConta().getDigitoAgencia())) {

                this.cliente.getBPBankAccounts().setControlKey(model.getBanco().getConta().getDigitoAgencia());

            }

            if (!TSUtil.isEmpty(model.getBanco().getConta().getDigitoConta())) {

                this.cliente.getBPBankAccounts().setUserNo1(model.getBanco().getConta().getDigitoConta());

            }

            if (!TSUtil.isEmpty(model.getBanco().getConta().getNumeroConta())) {

                this.cliente.getBPBankAccounts().setAccountNo(model.getBanco().getConta().getNumeroConta());

            }

            if (!TSUtil.isEmpty(model.getTipoConta())) {

                this.cliente.getBPBankAccounts().getUserFields().getFields().item("U_TIPO_CONTA").setValue(model.getTipoConta().toString());

            }

            retorno = this.cliente.update();

            if (retorno != 0) {

                errorMessage = conexaoSapUtil.getCompany().getLastError();

                System.err.println("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

                //model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

                throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

            } else {
                // x = x - System.currentTimeMillis();
                //System.out.println("Cliente cadastrado com sucesso. Tempo-->" + x.toString() );
            }

            this.cliente.release();

            this.cliente = null;

            //Runtime r = Runtime.getRuntime();

            //r.gc();

        }
        return model;
    }
    private void initObjetosNaRequisicao() throws TSApplicationException {
        this.conexaoSapUtil = ConexaoSapUtil.getConnection();
    }    
}
