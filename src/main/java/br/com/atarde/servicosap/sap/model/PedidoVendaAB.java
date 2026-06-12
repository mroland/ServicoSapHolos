package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import br.com.atarde.servicosap.model.TransferenciaEstoque;

@SuppressWarnings("serial")
public abstract class PedidoVendaAB extends DocumentoAB implements Serializable {

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
	private ParcelaPedidoVenda parcela;
	private List<ParcelaAB> parcelas;
	private BigDecimal percentualDesconto;
	private String observacao; // referente a comments

	private TransferenciaEstoque transferenciaEstoqueReferencia;

	private String arquivoRemessa;

	private boolean flagPedidoVenda = false;
	private boolean flagNotaFiscalSaida = false;

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

	public ParcelaPedidoVenda getParcela() {
		return parcela;
	}

	public void setParcela(ParcelaPedidoVenda parcela) {
		this.parcela = parcela;
	}

	public boolean isFlagPedidoVenda() {
		return flagPedidoVenda;
	}

	public void setFlagPedidoVenda(boolean flagPedidoVenda) {
		this.flagPedidoVenda = flagPedidoVenda;
	}

	public boolean isFlagNotaFiscalSaida() {
		return flagNotaFiscalSaida;
	}

	public void setFlagNotaFiscalSaida(boolean flagNotaFiscalSaida) {
		this.flagNotaFiscalSaida = flagNotaFiscalSaida;
	}

}
