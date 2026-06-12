package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class PedidoVenda extends PedidoVendaAB implements Serializable {

	private Long serialInicial;
	private Long serialFinal;
	private Long idInicial;
	private Long idFinal;
	private Date dataEmissao;
	private Date dataEmissaoInicial;
	private Date dataEmissaoFinal;
	private Boolean flagBoleto;
	private String arquivoUpload;
	private List<PedidoVendaLinha> linhas;
	private Usuario usuario;

	public PedidoVenda() {
	}

	public PedidoVenda(Long id) {
		this.setId(id);
	}

	public PedidoVenda(Empresa empresa) {

		this.setEmpresa(empresa);

	}

	public PedidoVenda(Status status) {

		this.setStatus(status);
	}

	public Long getSerialInicial() {
		return serialInicial;
	}

	public void setSerialInicial(Long serialInicial) {
		this.serialInicial = serialInicial;
	}

	public Long getSerialFinal() {
		return serialFinal;
	}

	public void setSerialFinal(Long serialFinal) {
		this.serialFinal = serialFinal;
	}

	public Long getIdInicial() {
		return idInicial;
	}

	public void setIdInicial(Long idInicial) {
		this.idInicial = idInicial;
	}

	public Long getIdFinal() {
		return idFinal;
	}

	public void setIdFinal(Long idFinal) {
		this.idFinal = idFinal;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}

	public void setDataEmissaoInicial(Date dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}

	public Date getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}

	public void setDataEmissaoFinal(Date dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}

	public Boolean getFlagBoleto() {
		return flagBoleto;
	}

	public void setFlagBoleto(Boolean flagBoleto) {
		this.flagBoleto = flagBoleto;
	}

	public String getArquivoUpload() {
		return arquivoUpload;
	}

	public void setArquivoUpload(String arquivoUpload) {
		this.arquivoUpload = arquivoUpload;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<PedidoVendaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<PedidoVendaLinha> linhas) {
		this.linhas = linhas;
	}

}
