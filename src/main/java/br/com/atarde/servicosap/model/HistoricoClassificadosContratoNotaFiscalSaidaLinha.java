/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.model;

import java.io.Serializable;

/**
 *
 * @author mroland
 */
@SuppressWarnings("serial")
public class HistoricoClassificadosContratoNotaFiscalSaidaLinha extends ClassificadosContratoNotaFiscalSaidaLinha implements Serializable{

    public HistoricoClassificadosContratoNotaFiscalSaidaLinha(ClassificadosContratoNotaFiscalSaida nota) {
        this.setNotaFiscalSaida(nota);
    }

    public HistoricoClassificadosContratoNotaFiscalSaidaLinha() {

    }



}