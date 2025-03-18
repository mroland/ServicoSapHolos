package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosap.model.FotografiaNotaFiscalSaida;
import br.com.atarde.servicosap.model.TransferenciaEstoque;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;

@SuppressWarnings("serial")
@XmlRootElement
@XmlSeeAlso({ AssinaturaNotaFiscalSaida.class, EasyclassNotaFiscalSaida.class, VendaAvulsaNotaFiscalSaida.class, FotografiaNotaFiscalSaida.class, DevolucaoNotaFiscalSaida.class })

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, // Identifica pelo nome da classe
		include = JsonTypeInfo.As.PROPERTY, property = "@class" // Nome do campo pode ser customizado
)
public abstract class NotaFiscalSaidaAB extends DocumentoAB implements Serializable {

	private Long interfaceId;
	private ParceiroNegocio cliente;
	private Long serial;
	private Vendedor vendedor;
	private Origem origem;
	private Date dataVencimento;
	private CondicaoPagamento condicaoPagamento;
	private BigDecimal uValorBruto;
	private BigDecimal valor;
	private String uEnderecoEntrega;
	private Sequencia sequencia;
	private ParcelaNotaFiscalSaida parcela;
	// private List<ParcelaNotaFiscalSaida> parcelas;
	private List<ParcelaAB> parcelas;
	private BigDecimal percentualDesconto;
	private PedidoVenda pedidoVenda;
	private String observacao; // referente a comments

	private TransferenciaEstoque transferenciaEstoqueReferencia;

	private String arquivoRemessa;

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public BigDecimal getUValorBruto() {
		return uValorBruto;
	}

	public void setUValorBruto(BigDecimal uValorBruto) {
		this.uValorBruto = uValorBruto;
	}

	public String getUEnderecoEntrega() {
		return uEnderecoEntrega;
	}

	public void setUEnderecoEntrega(String uEnderecoEntrega) {
		this.uEnderecoEntrega = uEnderecoEntrega;
	}

	public BigDecimal getuValorBruto() {
		return uValorBruto;
	}

	public void setuValorBruto(BigDecimal uValorBruto) {
		this.uValorBruto = uValorBruto;
	}

	public String getuEnderecoEntrega() {
		return uEnderecoEntrega;
	}

	public void setuEnderecoEntrega(String uEnderecoEntrega) {
		this.uEnderecoEntrega = uEnderecoEntrega;
	}

	public Sequencia getSequencia() {
		return sequencia;
	}

	public void setSequencia(Sequencia sequencia) {
		this.sequencia = sequencia;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public ParcelaNotaFiscalSaida getParcela() {
		return parcela;
	}

	public void setParcela(ParcelaNotaFiscalSaida parcela) {
		this.parcela = parcela;
	}

	public ParceiroNegocio getCliente() {
		return cliente;
	}

	public void setCliente(ParceiroNegocio cliente) {
		this.cliente = cliente;
	}

	public Long getSerial() {
		return serial;
	}

	public void setSerial(Long serial) {
		this.serial = serial;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public List<ParcelaAB> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaAB> parcelas) {
		this.parcelas = parcelas;
	}

	public TransferenciaEstoque getTransferenciaEstoqueReferencia() {
		return transferenciaEstoqueReferencia;
	}

	public void setTransferenciaEstoqueReferencia(TransferenciaEstoque transferenciaEstoqueReferencia) {
		this.transferenciaEstoqueReferencia = transferenciaEstoqueReferencia;
	}

	public String getArquivoRemessa() {
		return arquivoRemessa;
	}

	public void setArquivoRemessa(String arquivoRemessa) {
		this.arquivoRemessa = arquivoRemessa;
	}

}
