/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.model;

import java.io.Serializable;

import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaLinhaAB;
import br.com.atarde.servicosap.sap.model.PedidoVendaLinha;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class ClassificadosExecucaoNotaFiscalSaidaLinha extends NotaFiscalSaidaLinhaAB implements Serializable{

    private ClassificadosExecucaoNotaFiscalSaida notaFiscalSaida;
    
	private PedidoVendaLinha pedidoVendaLinha;      

    public ClassificadosExecucaoNotaFiscalSaidaLinha(){
        
    }

    public ClassificadosExecucaoNotaFiscalSaidaLinha(ClassificadosExecucaoNotaFiscalSaida notaFiscalSaida) {
        this.notaFiscalSaida = notaFiscalSaida;
    }

    public ClassificadosExecucaoNotaFiscalSaidaLinha(Long id) {
        this.setId(id);
    }

	public ClassificadosExecucaoNotaFiscalSaida getNotaFiscalSaida() {
		return notaFiscalSaida;
	}

	public void setNotaFiscalSaida(ClassificadosExecucaoNotaFiscalSaida notaFiscalSaida) {
		this.notaFiscalSaida = notaFiscalSaida;
	}

	public PedidoVendaLinha getPedidoVendaLinha() {
		return pedidoVendaLinha;
	}

	public void setPedidoVendaLinha(PedidoVendaLinha pedidoVendaLinha) {
		this.pedidoVendaLinha = pedidoVendaLinha;
	}

}
