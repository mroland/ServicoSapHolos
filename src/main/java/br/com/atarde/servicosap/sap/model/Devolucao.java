package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement
public class Devolucao extends DocumentoAB implements Serializable {

	private Long interfaceId;
    private Long serial;
    private Long serialInicial;
    private Long serialFinal;
    private Long idInicial;
    private Long idFinal;    
    private Date dataEmissao;
    private Date dataEmissaoInicial;
    private Date dataEmissaoFinal;
    private Boolean flagBoleto;
    private BigDecimal valor;
    private String arquivoUpload;
    private List<DevolucaoLinha> linhas; 
    private ParceiroNegocio cliente;  
    private ParcelaDevolucao parcela;
    private List<ParcelaDevolucao> parcelas;        

    public Devolucao() {
    }

    public Devolucao(Long id) {
        this.setId(id);
    }

	public Devolucao(Empresa empresa) {

		this.setEmpresa(empresa);
		
	}

	public Devolucao(Status status) {

		this.setStatus(status);
	}

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getArquivoUpload() {
		return arquivoUpload;
	}

	public void setArquivoUpload(String arquivoUpload) {
		this.arquivoUpload = arquivoUpload;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public ParceiroNegocio getCliente() {
		return cliente;
	}

	public void setCliente(ParceiroNegocio cliente) {
		this.cliente = cliente;
	}

	public List<DevolucaoLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<DevolucaoLinha> linhas) {
		this.linhas = linhas;
	}

	public ParcelaDevolucao getParcela() {
		return parcela;
	}

	public void setParcela(ParcelaDevolucao parcela) {
		this.parcela = parcela;
	}

	public List<ParcelaDevolucao> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaDevolucao> parcelas) {
		this.parcelas = parcelas;
	}

   
}
