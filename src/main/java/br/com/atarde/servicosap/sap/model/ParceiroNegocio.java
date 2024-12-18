/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class ParceiroNegocio extends PessoaAB implements Serializable {

	// tabela OCRD
	private ParceiroNegocioEndereco endereco;
	private List<String> emailsAdicionais;
	private String emailAdicional;
	private ParceiroNegocioContaBanco banco;
	private Empresa empresa;
	private Boolean flagAtivo;
	private Contato contato;
	private ContasReceber contasReceber;
	private String enderecoCobrancaDefault;
	private String enderecoEntregaDefault;
	private Boolean flagEndereco;

	public ParceiroNegocio() {
		// TODO Auto-generated constructor stub
	}

	public ParceiroNegocio(ParceiroNegocioContaBanco banco) {

		this.banco = banco;

	}

	public ParceiroNegocio(String atributo, String valor) {

		if ("id".equals(atributo)) {

			this.setId(valor);

		}
	}

	public List<String> getEmailsAdicionais() {
		return emailsAdicionais;
	}

	public void setEmailsAdicionais(List<String> emailsAdicionais) {
		this.emailsAdicionais = emailsAdicionais;
	}

	public String getEmailAdicional() {
		return emailAdicional;
	}

	public void setEmailAdicional(String emailAdicional) {
		this.emailAdicional = emailAdicional;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public ParceiroNegocioEndereco getEndereco() {
		return endereco;
	}

	public void setEndereco(ParceiroNegocioEndereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public ContasReceber getContasReceber() {
		return contasReceber;
	}

	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}

	public ParceiroNegocioContaBanco getBanco() {
		return banco;
	}

	public void setBanco(ParceiroNegocioContaBanco banco) {
		this.banco = banco;
	}

	public String getEnderecoCobrancaDefault() {
		return enderecoCobrancaDefault;
	}

	public void setEnderecoCobrancaDefault(String enderecoCobrancaDefault) {
		this.enderecoCobrancaDefault = enderecoCobrancaDefault;
	}

	public String getEnderecoEntregaDefault() {
		return enderecoEntregaDefault;
	}

	public void setEnderecoEntregaDefault(String enderecoEntregaDefault) {
		this.enderecoEntregaDefault = enderecoEntregaDefault;
	}

	public Boolean getFlagEndereco() {
		return flagEndereco;
	}

	public void setFlagEndereco(Boolean flagEndereco) {
		this.flagEndereco = flagEndereco;
	}

}
