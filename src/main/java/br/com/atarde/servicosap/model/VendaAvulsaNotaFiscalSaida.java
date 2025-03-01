/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosap.sap.model.Status;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
@XmlRootElement
public class VendaAvulsaNotaFiscalSaida extends NotaFiscalSaidaAB implements Serializable {

	private Boolean flagConsignado;

	private String uBanca;

	private String uLote;

	private String uRdj;

	private String uTipoBanca;

	private String uTipoFaturamento;

	private String uObservacao;

	private List<VendaAvulsaNotaFiscalSaidaRomaneio> romaneios;

	private List<VendaAvulsaNotaFiscalSaidaLinha> linhas;

	public VendaAvulsaNotaFiscalSaida() {

	}

	public VendaAvulsaNotaFiscalSaida(Status status) {

		this.setStatus(status);

	}

	public VendaAvulsaNotaFiscalSaida(Long id) {
		this.setId(id);
	}

	public VendaAvulsaNotaFiscalSaida(String atributo, Long interfaceId) {

		if ("interfaceId".equals(atributo)) {

			this.setInterfaceId(interfaceId);

		}

	}

	public VendaAvulsaNotaFiscalSaida(Empresa empresa) {
		this.setEmpresa(empresa);
	}

	public VendaAvulsaNotaFiscalSaida(Empresa empresa, Status status) {

		this.setEmpresa(empresa);

		this.setStatus(status);

	}

	public String getUBanca() {
		return uBanca;
	}

	public void setUBanca(String banca) {
		uBanca = banca;
	}

	public String getURdj() {
		return uRdj;
	}

	public String getuBanca() {
		return uBanca;
	}

	public void setuBanca(String uBanca) {
		this.uBanca = uBanca;
	}

	public String getuLote() {
		return uLote;
	}

	public void setuLote(String uLote) {
		this.uLote = uLote;
	}

	public String getuRdj() {
		return uRdj;
	}

	public void setuRdj(String uRdj) {
		this.uRdj = uRdj;
	}

	public String getuTipoBanca() {
		return uTipoBanca;
	}

	public void setuTipoBanca(String uTipoBanca) {
		this.uTipoBanca = uTipoBanca;
	}

	public String getuTipoFaturamento() {
		return uTipoFaturamento;
	}

	public void setuTipoFaturamento(String uTipoFaturamento) {
		this.uTipoFaturamento = uTipoFaturamento;
	}

	public String getuObservacao() {
		return uObservacao;
	}

	public void setuObservacao(String uObservacao) {
		this.uObservacao = uObservacao;
	}

	public void setURdj(String rdj) {
		uRdj = rdj;
	}

	public String getUTipoBanca() {
		return uTipoBanca;
	}

	public void setUTipoBanca(String tipoBanca) {
		uTipoBanca = tipoBanca;
	}

	public String getUTipoFaturamento() {
		return uTipoFaturamento;
	}

	public void setUTipoFaturamento(String tipoFaturamento) {
		uTipoFaturamento = tipoFaturamento;
	}

	public String getUObservacao() {
		return uObservacao;
	}

	public void setUObservacao(String uObservacao) {
		this.uObservacao = uObservacao;
	}

	public List<VendaAvulsaNotaFiscalSaidaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<VendaAvulsaNotaFiscalSaidaLinha> linhas) {
		this.linhas = linhas;
	}

	public List<VendaAvulsaNotaFiscalSaidaRomaneio> getRomaneios() {
		return romaneios;
	}

	public void setRomaneios(List<VendaAvulsaNotaFiscalSaidaRomaneio> romaneios) {
		this.romaneios = romaneios;
	}

	public String getULote() {
		return uLote;
	}

	public void setULote(String uLote) {
		this.uLote = uLote;
	}

	public Boolean getFlagConsignado() {
		return flagConsignado;
	}

	public void setFlagConsignado(Boolean flagConsignado) {
		this.flagConsignado = flagConsignado;
	}

}
