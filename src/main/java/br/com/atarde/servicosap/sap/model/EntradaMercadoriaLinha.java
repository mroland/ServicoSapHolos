package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class EntradaMercadoriaLinha implements Serializable{
	
	//Tabela IGN1
	
	private Long id;
	private Long interfaceId;
	private EntradaMercadoria entradaMercadoria;
    private Item item;
    private Double quantidade;
    private BigDecimal valorUnitario;
    private ContaContabil contaContabil;
    private Empresa empresa;
    private String codigoBarras;
    private Estoque estoque;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public ContaContabil getContaContabil() {
		return contaContabil;
	}
	public void setContaContabil(ContaContabil contaContabil) {
		this.contaContabil = contaContabil;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public EntradaMercadoria getEntradaMercadoria() {
		return entradaMercadoria;
	}
	public void setEntradaMercadoria(EntradaMercadoria entradaMercadoria) {
		this.entradaMercadoria = entradaMercadoria;
	}	

    
}
