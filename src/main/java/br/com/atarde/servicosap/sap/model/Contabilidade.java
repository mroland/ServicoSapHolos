package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Contabilidade implements Serializable{
	
	private Long interfaceId;
	
	private Long id;
	
	private Date dataVencimento;
	
	private String observacao;
	
	private Date dataLancamento;
	
	private Date dataDocumento;
	
	private List<ContabilidadeLinha> linhas;
	
	private Empresa empresa;
	
	private Status status;

	private Date dataImportacao;

	private Date dataExportacao;

	private String mensagemErro;
	
	private Date dataAtualizacao; 
	
	private String referencia2;
	
	public Contabilidade(){
		
	}

	public Contabilidade(Status status) {

		this.status = status;
	}

	public Contabilidade(Empresa empresa) {
		this.empresa = empresa;
	}

	public Contabilidade(Long id) {
		this.id = id;
	}

	public Contabilidade(String atributo, Long valor) {
		if("interfaceId".equals(atributo)){
			
			this.interfaceId = valor;
			
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDataImportacao() {
		return dataImportacao;
	}

	public void setDataImportacao(Date dataImportacao) {
		this.dataImportacao = dataImportacao;
	}

	public Date getDataExportacao() {
		return dataExportacao;
	}

	public void setDataExportacao(Date dataExportacao) {
		this.dataExportacao = dataExportacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<ContabilidadeLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<ContabilidadeLinha> linhas) {
		this.linhas = linhas;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public String getReferencia2() {
		return referencia2;
	}

	public void setReferencia2(String referencia2) {
		this.referencia2 = referencia2;
	}

	

}
