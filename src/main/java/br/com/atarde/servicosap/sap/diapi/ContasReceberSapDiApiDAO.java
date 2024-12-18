package br.com.atarde.servicosap.sap.diapi;

import br.com.atarde.servicosap.sap.dao.BoletoDAO;
import br.com.atarde.servicosap.sap.model.Boleto;
import br.com.atarde.servicosap.sap.model.ContasReceber;
import br.com.atarde.servicosap.sap.model.ContasReceberLinha;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.ModalidadePagamentoBoleto;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.atarde.servicosap.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

import com.sap.smb.sbo.api.BillOfExchangeTransaction;
import com.sap.smb.sbo.api.Payments;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;

public class ContasReceberSapDiApiDAO {

    private Payments oPayments;
    private ConexaoSapUtil conexaoSapUtil;
    private SBOErrorMessage errorMessage;
    private BillOfExchangeTransaction oBillOfExchangeTransaction;
	private Empresa empresa;

	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}

	}    

    public void inserirTransferencia(ContasReceber model) throws TSApplicationException {

        int contador, retorno;
        
		this.initObjetosNaRequisicao(model.getEmpresa());        
        
        this.oPayments = new Payments(conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oIncomingPayments));        

        this.oPayments.setDocObjectCode(SBOCOMConstants.BoPaymentsObjectType_bopot_IncomingPayments);

        this.oPayments.setDocType(SBOCOMConstants.BoRcptTypes_rCustomer);

        this.oPayments.setCardCode(model.getCliente().getId());

        this.oPayments.setTaxDate(model.getDataDocumento());

        this.oPayments.setDocDate(model.getDataLancamento());

        this.oPayments.setDueDate(model.getDataVencimento());

        contador = 0;
        
        for (ContasReceberLinha linha : model.getLinhas()) {

                if (contador > 0) {

                    this.oPayments.getInvoices().add();

                    this.oPayments.getInvoices().setCurrentLine(contador);

                }

                this.oPayments.getInvoices().setDocEntry(linha.getParcela().getId().intValue());

                this.oPayments.getInvoices().setSumApplied(linha.getValorAplicado().doubleValue());

                this.oPayments.getInvoices().setInstallmentId(linha.getParcela().getNumero());

                this.oPayments.getInvoices().setInvoiceType(SBOCOMConstants.BoRcptInvTypes_it_Invoice);

                contador++;
		
		}

        this.oPayments.setTransferAccount(model.getModalidadePagamentoTransferencia().getContaContabil().getId());

        this.oPayments.setTransferSum(model.getModalidadePagamentoTransferencia().getValor().doubleValue());

        this.oPayments.setTransferDate(model.getModalidadePagamentoTransferencia().getDataTransferencia());

        if (!TSUtil.isEmpty(model.getModalidadePagamentoTransferencia().getReferencia())) {

            this.oPayments.setTransferReference(model.getModalidadePagamentoTransferencia().getReferencia());
        }

        retorno = this.oPayments.add();

        if (retorno != 0) {

            errorMessage = conexaoSapUtil.getCompany().getLastError();

            throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

        } else {
            //System.out.println("Nota cadastrada com Sucesso!!, uHuuuuuu");
        }

        this.oPayments.release();

        this.oPayments = null;

        //Runtime r = Runtime.getRuntime();

       // r.gc();

    }

    public void inserirBoleto(ContasReceber model) throws TSApplicationException {

        int contador, retorno;
        
		this.initObjetosNaRequisicao(model.getEmpresa());        
        
        this.oPayments = new Payments(conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oIncomingPayments));        

        this.oPayments.setDocObjectCode(SBOCOMConstants.BoPaymentsObjectType_bopot_IncomingPayments);

        this.oPayments.setDocType(SBOCOMConstants.BoRcptTypes_rCustomer);

        this.oPayments.setCardCode(model.getCliente().getId());

        this.oPayments.setTaxDate(model.getDataDocumento());

        this.oPayments.setDocDate(model.getDataLancamento());

        this.oPayments.setDueDate(model.getDataVencimento());

        contador = 0;

        for (ContasReceberLinha linha : model.getLinhas()) {

            if (contador > 0) {

                this.oPayments.getInvoices().add();

                this.oPayments.getInvoices().setCurrentLine(contador);

            }

            this.oPayments.getInvoices().setDocEntry(linha.getParcela().getId().intValue());

            this.oPayments.getInvoices().setSumApplied(linha.getValorAplicado().doubleValue());

            this.oPayments.getInvoices().setInstallmentId(linha.getParcela().getNumero());

            this.oPayments.getInvoices().setInvoiceType(SBOCOMConstants.BoRcptInvTypes_it_Invoice);

            contador++;

        }

        this.oPayments.setBillOfExchangeAmount(model.getModalidadePagamentoBoleto().getValor().doubleValue());

        this.oPayments.setBillofExchangeStatus(SBOCOMConstants.BoBoeStatus_boes_Created);

        this.oPayments.getBillOfExchange().setBillOfExchangeDueDate(model.getModalidadePagamentoBoleto().getDataVencimento());

        this.oPayments.getBillOfExchange().setPaymentMethodCode(model.getModalidadePagamentoBoleto().getFormaPagamento().getId());

        //this.oPayments.setBoeAccount(modalidade.getContaContabilModel().getId());

        if (!TSUtil.isEmpty(model.getModalidadePagamentoBoleto().getReferencia())) {

            this.oPayments.getBillOfExchange().setReferenceNo(model.getModalidadePagamentoBoleto().getReferencia());
            
        }

        retorno = this.oPayments.add();

        if (retorno != 0) {

            errorMessage = conexaoSapUtil.getCompany().getLastError();

            throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

        } else {
            //System.out.println("Nota cadastrada com Sucesso!!, uHuuuuuu");
        }

        this.oPayments.release();

        this.oPayments = null;

        //Runtime r = Runtime.getRuntime();

        //r.gc();

    }

    public void cancelar(ContasReceber model) throws TSApplicationException {

        int retorno;
        
		this.initObjetosNaRequisicao(model.getEmpresa());        
        
        this.oPayments = new Payments(conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oIncomingPayments));        

        if (this.oPayments.getByKey(model.getId().intValue())) {

            retorno = this.oPayments.cancel();

            if (retorno != 0) {

                errorMessage = conexaoSapUtil.getCompany().getLastError();

                throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

                //model.setMensagemErroImportacao("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

            } else {
                //System.out.println("Nota cadastrada com Sucesso!!, uHuuuuuu");
            }

            this.oPayments.release();

            this.oPayments = null;

            //Runtime r = Runtime.getRuntime();

            //r.gc();

        }

    }

    public void cancelarBoleto(ContasReceber model) throws TSApplicationException {

        int retorno;

        ModalidadePagamentoBoleto modalidade = (ModalidadePagamentoBoleto) model.getModalidadePagamentoBoleto();
        
		this.initObjetosNaRequisicao(model.getEmpresa());        

        this.oBillOfExchangeTransaction = new BillOfExchangeTransaction(conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oBillOfExchangeTransactions));

        this.oBillOfExchangeTransaction.getLines().setBillOfExchangeNo(modalidade.getBoleto().getId().intValue());

        this.oBillOfExchangeTransaction.getLines().setBillOfExchangeType(SBOCOMConstants.BoBOETypes_Incoming);
        
        model.getModalidadePagamentoBoleto().getBoleto().setEmpresa(model.getEmpresa());
        
        Boleto boleto = new BoletoDAO().obter(model.getModalidadePagamentoBoleto().getBoleto());
        
        if(Constantes.STATUS_BOLETO_PAGO.equals(boleto.getStatus().getId())){
        	
            this.oBillOfExchangeTransaction.setStatusFrom(SBOCOMConstants.BoBOTFromStatus_btfs_Paid);

            this.oBillOfExchangeTransaction.setStatusTo(SBOCOMConstants.BoBOTToStatus_btts_Deposit);
            
			retorno = this.salvarBoleto();

			if (retorno == 0) {

				this.cancelarBoleto(model);

			}            
        	
        }
        
        if(Constantes.STATUS_BOLETO_DEPOSITADO.equals(boleto.getStatus().getId())){
        	
            this.oBillOfExchangeTransaction.setStatusFrom(SBOCOMConstants.BoBOTFromStatus_btfs_Deposited);

            this.oBillOfExchangeTransaction.setStatusTo(SBOCOMConstants.BoBOTToStatus_btts_Generated);
            
			retorno = this.salvarBoleto();

			if (retorno == 0) {

				this.cancelarBoleto(model);

			}            
        	
        }
        
        if(Constantes.STATUS_BOLETO_GERADO.equals(boleto.getStatus().getId())){
        	
            this.oBillOfExchangeTransaction.setStatusFrom(SBOCOMConstants.BoBOTFromStatus_btfs_Generated);

            this.oBillOfExchangeTransaction.setStatusTo(SBOCOMConstants.BoBOTToStatus_btts_Canceled);
            
			retorno = this.salvarBoleto();

			if (retorno == 0) {

				this.cancelarBoleto(model);

			}            
        	
        }        

        this.oBillOfExchangeTransaction.release();

        this.oBillOfExchangeTransaction = null;

        //Runtime r = Runtime.getRuntime();

        //r.gc();

    }
    
	private int salvarBoleto() throws TSApplicationException {

		int retorno;

		retorno = this.oBillOfExchangeTransaction.add();

		if (retorno != 0) {

			errorMessage = conexaoSapUtil.getCompany().getLastError();

			throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

		} else {
			// System.out.println("Nota cadastrada com Sucesso!!, uHuuuuuu");
		}

		return retorno;
	}    

}
