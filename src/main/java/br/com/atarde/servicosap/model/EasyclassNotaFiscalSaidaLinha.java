/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.atarde.servicosap.sap.model.PedidoVendaLinha;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class EasyclassNotaFiscalSaidaLinha extends NotaFiscalSaidaLinhaAB implements Serializable{

    private String uCmXCol;

    private BigDecimal uArea;

    private Integer uQuantidadeInsercoes;

    private BigDecimal uTotalCmXCol;

    private BigDecimal uValorUnitario;

    private EasyclassNotaFiscalSaida notaFiscalSaida;
    
	private PedidoVendaLinha pedidoVendaLinha;      

    public EasyclassNotaFiscalSaidaLinha(){
        
    }

    public EasyclassNotaFiscalSaidaLinha(EasyclassNotaFiscalSaida notaFiscalSaida) {
        this.notaFiscalSaida = notaFiscalSaida;
    }

    public EasyclassNotaFiscalSaidaLinha(Long id) {
        this.setId(id);
    }

	public String getUCmXCol() {
		return uCmXCol;
	}

	public void setUCmXCol(String uCmXCol) {
		this.uCmXCol = uCmXCol;
	}

	public BigDecimal getUArea() {
		return uArea;
	}

	public void setUArea(BigDecimal uArea) {
		this.uArea = uArea;
	}

	public Integer getUQuantidadeInsercoes() {
		return uQuantidadeInsercoes;
	}

	public void setUQuantidadeInsercoes(Integer uQuantidadeInsercoes) {
		this.uQuantidadeInsercoes = uQuantidadeInsercoes;
	}

	public BigDecimal getUTotalCmXCol() {
		return uTotalCmXCol;
	}

	public void setUTotalCmXCol(BigDecimal uTotalCmXCol) {
		this.uTotalCmXCol = uTotalCmXCol;
	}

	public BigDecimal getUValorUnitario() {
		return uValorUnitario;
	}

	public void setUValorUnitario(BigDecimal uValorUnitario) {
		this.uValorUnitario = uValorUnitario;
	}

	public EasyclassNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(EasyclassNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

	public PedidoVendaLinha getPedidoVendaLinha() {
		return pedidoVendaLinha;
	}

	public void setPedidoVendaLinha(PedidoVendaLinha pedidoVendaLinha) {
		this.pedidoVendaLinha = pedidoVendaLinha;
	}

}
