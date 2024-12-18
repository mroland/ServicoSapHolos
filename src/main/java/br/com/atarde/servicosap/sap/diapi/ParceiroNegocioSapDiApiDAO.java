/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.sap.diapi;

import br.com.atarde.servicosap.sap.dao.FormaPagamentoDAO;
import br.com.atarde.servicosap.sap.dao.IdentificadorFiscalDAO;
import br.com.atarde.servicosap.sap.dao.ParceiroNegocioEnderecoDAO;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.FormaPagamento;
import br.com.atarde.servicosap.sap.model.IdentificadorFiscal;
import br.com.atarde.servicosap.sap.model.ParceiroNegocio;
import br.com.atarde.servicosap.util.ConexaoSapUtil;
import br.com.atarde.servicosap.util.Constantes;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

import com.sap.smb.sbo.api.BusinessPartners;
import com.sap.smb.sbo.api.SBOCOMConstants;
import com.sap.smb.sbo.api.SBOErrorMessage;

/**
 * 
 * @author mroland
 */
public class ParceiroNegocioSapDiApiDAO {

	private ConexaoSapUtil conexaoSapUtil;
	private BusinessPartners cliente;
	private SBOErrorMessage errorMessage;
	private int retorno;
	private Empresa empresa;
	
	private void initObjetosNaRequisicao(Empresa model) throws TSApplicationException {

		this.empresa = model;

		if (!TSUtil.isEmpty(this.empresa)) {

			this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);

		}

	}	

	public ParceiroNegocio inserirComEndereco(ParceiroNegocio model) throws TSApplicationException {

		this.initObjetosNaRequisicao(model.getEmpresa());

		this.cliente = new BusinessPartners(this.conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oBusinessPartners));

		this.inserirCliente(model);

		this.inserirFormaPagamento(model);

		IdentificadorFiscal identificadorFiscal;

		identificadorFiscal = new IdentificadorFiscal();

		identificadorFiscal.setParceiroNegocio(model);

		identificadorFiscal.setEmpresa(model.getEmpresa());

		if (TSUtil.isEmpty(new IdentificadorFiscalDAO().obterEnderecoNulo(identificadorFiscal))) {

			// para address='' --> default

			model.getIdentificadorFiscal().setEnderecoId("");

			this.inserirIdentificadorFiscal(identificadorFiscal);

		}

		model.getEndereco().setTipoEndereco("S");

		model.getEndereco().setId("Entrega" + " - " + model.getId() + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());

		this.inserirEndereco(model);

		this.cliente.getAddresses().add();

		model.getEndereco().setTipoEndereco("B");
		
		model.getEndereco().setId("Cobranca" + " - " + model.getId() + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());
		
		this.inserirEndereco(model);

		retorno = this.cliente.add();

		if (retorno != 0) {

			errorMessage = conexaoSapUtil.getCompany().getLastError();

			System.err.println("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

			model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

			throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

		} else {

			model.setId(conexaoSapUtil.getCompany().getNewObjectCode());

		}

		this.cliente.release();

		this.cliente = null;

		//Runtime r = Runtime.getRuntime();

		//r.gc();

		return model;

	}

	public ParceiroNegocio inserirSemEndereco(ParceiroNegocio model) throws TSApplicationException {

		this.initObjetosNaRequisicao(model.getEmpresa());

		this.cliente = new BusinessPartners(this.conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oBusinessPartners));

		this.inserirCliente(model);

		this.inserirFormaPagamento(model);

		IdentificadorFiscal identificadorFiscalModel;

		identificadorFiscalModel = new IdentificadorFiscal();

		model.getIdentificadorFiscal().setEnderecoId("");

		identificadorFiscalModel.setParceiroNegocio(model);

		this.inserirIdentificadorFiscal(identificadorFiscalModel);

		retorno = this.cliente.add();

		if (retorno != 0) {

			errorMessage = conexaoSapUtil.getCompany().getLastError();

			System.err.println("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

			model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

			throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

		} else {

			model.setId(conexaoSapUtil.getCompany().getNewObjectCode());
			
		}

		this.cliente.release();

		this.cliente = null;

		//Runtime r = Runtime.getRuntime();

		//r.gc();

		return model;

	}

	public ParceiroNegocio inserirEnderecoCliente(ParceiroNegocio model) throws TSApplicationException {

		this.initObjetosNaRequisicao(model.getEmpresa());

		this.cliente = new BusinessPartners(this.conexaoSapUtil.getCompany().getBusinessObject(SBOCOMConstants.BoObjectTypes_oBusinessPartners));

		if (this.cliente.getByKey(model.getId())) {
			
			if (!TSUtil.isEmpty(new ParceiroNegocioEnderecoDAO().pesquisar(model))) {

				this.cliente.getAddresses().add();

			}			

			this.inserirEndereco(model);

			retorno = this.cliente.update();

			if (retorno != 0) {

				errorMessage = conexaoSapUtil.getCompany().getLastError();

				System.err.println("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

				model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

				throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

			}

			this.cliente.release();

			this.cliente = null;

			//Runtime r = Runtime.getRuntime();

			//r.gc();

			return model;

		}

		return null;

	}	
	
	private void inserirCliente(ParceiroNegocio model) {

		this.cliente.setCardName(model.getNome());

		if (Constantes.TIPO_PARCEIRO_NEGOCIO_CLIENTE.equals(model.getTipo())) {

			this.cliente.setCardType(SBOCOMConstants.BoCardTypes_cCustomer);

			this.cliente.setCardCode("C" + model.getIdentificadorFiscal().getIdentificador());

		} else {

			this.cliente.setCardCode("F" + model.getIdentificadorFiscal().getIdentificador());

			this.cliente.setCardType(SBOCOMConstants.BoCardTypes_cSupplier);
		}

		if (!TSUtil.isEmpty(model.getNomeFantasia())) {

			this.cliente.setCardForeignName(model.getNomeFantasia());

		}

		if (!TSUtil.isEmpty(model.getTelefoneResidencial())) {

			this.cliente.setPhone1(model.getTelefoneResidencial());

		}

		if (!TSUtil.isEmpty(model.getTelefoneCelular())) {

			this.cliente.setCellular(model.getTelefoneCelular());

		}

		if (!TSUtil.isEmpty(model.getFax())) {

			this.cliente.setFax(model.getFax());

		}

		if (!TSUtil.isEmpty(model.getEmail())) {

			this.cliente.setEmailAddress(model.getEmail());

		}

		if (!TSUtil.isEmpty(model.getObservacao())) {

			this.cliente.setFreeText(model.getObservacao());

		}

		// ClassificacaoId

		this.cliente.setGroupCode(model.getClassificacao().getId().intValue());

		model.setId(cliente.getCardCode());

	}

	private void inserirFormaPagamento(ParceiroNegocio model) {

		int contador = 0;

		FormaPagamento forma = new FormaPagamento();

		forma.setEmpresa(model.getEmpresa());

		if (Constantes.TIPO_PARCEIRO_NEGOCIO_CLIENTE.equals(model.getTipo())) {

			forma.setTipo("I");

		} else {

			forma.setTipo("O");

		}

		for (FormaPagamento item : new FormaPagamentoDAO().pesquisar(forma)) {

			if (contador > 0) {

				this.cliente.getBPPaymentMethods().add();

			}

			this.cliente.getBPPaymentMethods().setPaymentMethodCode(item.getId());

			contador++;

		}

	}

	private void inserirEndereco(ParceiroNegocio model) {

		IdentificadorFiscal identificadorFiscalModel;
		
		this.cliente.getAddresses().setAddressName(model.getEndereco().getId());		

		if (model.getEndereco().getTipoEndereco().equals("S")) {

			// para address = ShipTo

			this.cliente.getAddresses().setAddressType(SBOCOMConstants.BoAddressType_bo_ShipTo);

			this.cliente.getFiscalTaxID().add();

			identificadorFiscalModel = new IdentificadorFiscal();

			model.getIdentificadorFiscal().setEnderecoId(this.cliente.getAddresses().getAddressName());

			identificadorFiscalModel.setParceiroNegocio(model);

			this.inserirIdentificadorFiscal(identificadorFiscalModel);

		} else {

			// BillTo

			this.cliente.getAddresses().setAddressType(SBOCOMConstants.BoAddressType_bo_BillTo);

		}
		
		if(!TSUtil.isEmpty(model.getEndereco().getTipoLogradouro())){
			
			this.cliente.getAddresses().setTypeOfAddress(model.getEndereco().getTipoLogradouro());
			
		}

		if (!TSUtil.isEmpty(model.getEndereco().getBairro())) {

			this.cliente.getAddresses().setBlock(model.getEndereco().getBairro());

		}

		this.cliente.getAddresses().setStreet(model.getEndereco().getLogradouro());

		this.cliente.getAddresses().setCounty(model.getEndereco().getMunicipio().getId().toString());

		this.cliente.getAddresses().setStreetNo(model.getEndereco().getNumero());

		if (!TSUtil.isEmpty(model.getEndereco().getComplemento())) {

			cliente.getAddresses().setBuildingFloorRoom(model.getEndereco().getComplemento());

		}

		this.cliente.getAddresses().setCity(model.getEndereco().getCidade());

		this.cliente.getAddresses().setZipCode(model.getEndereco().getCep());

		this.cliente.getAddresses().setState(model.getEndereco().getEstado().getId());

		this.cliente.getAddresses().setCountry(model.getEndereco().getPais().getId());

	}

	private void inserirIdentificadorFiscal(IdentificadorFiscal model) {

		this.cliente.getFiscalTaxID().setAddress(model.getParceiroNegocio().getIdentificadorFiscal().getEnderecoId());

		switch (model.getParceiroNegocio().getIdentificadorFiscal().getTipoIdentificador().intValue()) {

		case 0:// CNPJ

			this.cliente.getFiscalTaxID().setTaxId0(model.getParceiroNegocio().getIdentificadorFiscal().getIdentificador());

			break;

		case 1:// CPF

			this.cliente.getFiscalTaxID().setTaxId4(model.getParceiroNegocio().getIdentificadorFiscal().getIdentificador());

			break;

		case 2:// OUTROS

			this.cliente.getFiscalTaxID().setTaxId5(model.getParceiroNegocio().getIdentificadorFiscal().getIdentificador());

			break;

		}

		if (!TSUtil.isEmpty(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadual())) {

			this.cliente.getFiscalTaxID().setTaxId1(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadual());

		}

		if (!TSUtil.isEmpty(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria())) {

			this.cliente.getFiscalTaxID().setTaxId2(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoEstadualSubstitutoTributaria());

		}

		if (!TSUtil.isEmpty(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoMunicipal())) {

			this.cliente.getFiscalTaxID().setTaxId3(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoMunicipal());

		}

		if (!TSUtil.isEmpty(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoINSS())) {

			this.cliente.getFiscalTaxID().setTaxId7(model.getParceiroNegocio().getIdentificadorFiscal().getInscricaoINSS());

		}

	}

	public ParceiroNegocio atualizar(ParceiroNegocio model) throws TSApplicationException {

		Long x = System.currentTimeMillis();

		if (this.cliente.getByKey(model.getId())) {

			if (!TSUtil.isEmpty(model.getEmail())) {

				this.cliente.setEmailAddress(model.getEmail());

				retorno = this.cliente.update();

				if (retorno != 0) {

					errorMessage = conexaoSapUtil.getCompany().getLastError();

					System.err.println("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

					model.setMensagemErro("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

					throw new TSApplicationException("Error " + errorMessage.getErrorCode() + " : " + errorMessage.getErrorMessage());

				} else {

					x = x - System.currentTimeMillis();

					// System.out.println("Cliente cadastrado com sucesso. Tempo-->"
					// + x.toString() );
				}

				this.cliente.release();

				this.cliente = null;

				//Runtime r = Runtime.getRuntime();

				//r.gc();

				return model;

			}

		}

		return model;
	}

}
