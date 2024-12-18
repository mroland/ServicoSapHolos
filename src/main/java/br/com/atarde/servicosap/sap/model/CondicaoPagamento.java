package br.com.atarde.servicosap.sap.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@SuppressWarnings("serial")
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class CondicaoPagamento implements Serializable{
	
	// Tabela OCTG	
	
	private Long id;
	
	private String descricao;
	
	private Empresa empresa;
	
	private String mensagemErro;
	
	private Integer diasVencimento;	
	
	private Integer quantidadeParcelas;
	
	private List<ParcelaAB> parcelas;

	public Long getId() {
            return id;
	}

	public void setId(Long id) {
            this.id = id;
	}

	public String getDescricao() {
            return descricao;
	}

	public void setDescricao(String descricao) {
            this.descricao = descricao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public Integer getDiasVencimento() {
		return diasVencimento;
	}

	public void setDiasVencimento(Integer diasVencimento) {
		this.diasVencimento = diasVencimento;
	}

	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public List<ParcelaAB> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<ParcelaAB> parcelas) {
		this.parcelas = parcelas;
	}




}
