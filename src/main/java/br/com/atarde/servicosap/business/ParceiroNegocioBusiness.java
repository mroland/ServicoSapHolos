/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.business;

import br.com.atarde.servicosap.sap.dao.ParceiroNegocioDAO;
import br.com.atarde.servicosap.sap.dao.ParceiroNegocioEnderecoDAO;
import br.com.atarde.servicosap.sap.diapi.ParceiroNegocioSapDiApiDAO;
import br.com.atarde.servicosap.sap.model.ParceiroNegocio;
import br.com.atarde.servicosap.sap.model.ParceiroNegocioEndereco;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

/**
 * 
 * @author mroland
 */
public class ParceiroNegocioBusiness {

	public ParceiroNegocio validarClienteComEndereco(ParceiroNegocio model) throws TSApplicationException {
		
		ParceiroNegocio parceiroNegocio;

		if (!TSUtil.isEmpty(Utilitarios.tratarString(model.getId()))) {

			parceiroNegocio = new ParceiroNegocio();

			parceiroNegocio.setId(model.getId());

		} else {

			parceiroNegocio = new ParceiroNegocioDAO().obterPorIdentificador(model);

		}

		if (TSUtil.isEmpty(parceiroNegocio)) {

			new ParceiroNegocioSapDiApiDAO().inserirComEndereco(model);

		} else {
			
			model.setId(parceiroNegocio.getId());
			
			if(TSUtil.isEmpty(parceiroNegocio.getEnderecoEntregaDefault())){
				
				if(model.getFlagEndereco()){
								
					this.inserirEnderecoEntrega(model);
					
				}
				
			}else{
				
				model.setEnderecoEntregaDefault(parceiroNegocio.getEnderecoEntregaDefault());
				
			}
			
			if(TSUtil.isEmpty(parceiroNegocio.getEnderecoCobrancaDefault())){
				
				if(model.getFlagEndereco()){
					
					this.inserirEnderecoCobranca(model);
					
				}
				
			}else{
				
				model.setEnderecoCobrancaDefault(parceiroNegocio.getEnderecoCobrancaDefault());
				
			}			
			
		}

		return model;

	}

	private void inserirEnderecoCobranca(ParceiroNegocio model) throws TSApplicationException {

		ParceiroNegocioEndereco endereco = new ParceiroNegocioEndereco();

		endereco.setParceiroNegocio(new ParceiroNegocio("id", model.getId()));

		endereco.setEmpresa(model.getEmpresa());

		endereco.setTipoEndereco("B");

		endereco.setId("Cobranca" + " - " + model.getId() + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());

		endereco = new ParceiroNegocioEnderecoDAO().obter(endereco);

		if (TSUtil.isEmpty(endereco) && TSUtil.isEmpty(model.getEnderecoCobrancaDefault())) {

			model.getEndereco().setTipoEndereco("B");

			model.getEndereco().setId("Cobranca" + " - " + model.getId() + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());

			new ParceiroNegocioSapDiApiDAO().inserirEnderecoCliente(model);

		}
		
		model.setEnderecoCobrancaDefault("Cobranca" + " - " + model.getId() + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());
		
	}

	private void inserirEnderecoEntrega(ParceiroNegocio model) throws TSApplicationException {

		ParceiroNegocioEndereco endereco = new ParceiroNegocioEndereco();

		endereco.setParceiroNegocio(new ParceiroNegocio("id", model.getId()));

		endereco.setEmpresa(model.getEmpresa());

		endereco.setTipoEndereco("S");

		endereco.setId("Entrega" + " - " + model.getId() + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());

		endereco = new ParceiroNegocioEnderecoDAO().obter(endereco);

		if (TSUtil.isEmpty(endereco) && TSUtil.isEmpty(model.getEnderecoEntregaDefault())) {

			model.getEndereco().setTipoEndereco("S");

			model.getEndereco().setId("Entrega" + " - " + model.getId() + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());

			new ParceiroNegocioSapDiApiDAO().inserirEnderecoCliente(model);

		}
		
		model.setEnderecoEntregaDefault("Entrega" + " - " + model.getId() + "." + model.getEndereco().getCep() + "." + model.getEndereco().getNumero());		

		
	}

	protected ParceiroNegocio validarClienteSemEndereco(ParceiroNegocio model) throws TSApplicationException {

		ParceiroNegocio parceiroNegocioModel = new ParceiroNegocioDAO().obterPorIdentificador(model);

		if (TSUtil.isEmpty(parceiroNegocioModel)) {

			model = new ParceiroNegocioSapDiApiDAO().inserirSemEndereco(model);

		}

		return model;

	}

}
