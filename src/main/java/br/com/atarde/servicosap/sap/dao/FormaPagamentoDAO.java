/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.sap.dao;

import java.util.List;

import br.com.atarde.servicosap.sap.model.FormaPagamento;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class FormaPagamentoDAO {

    @SuppressWarnings("unchecked")
	public List<FormaPagamento> pesquisar(FormaPagamento model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("formapagamentosapdao.pesquisar", model.getTipo());

        return broker.getCollectionBean(FormaPagamento.class, "id");
        
    }
    
    public FormaPagamento obter(FormaPagamento model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("formapagamentosapdao.obter", model.getId(), model.getTipo());

        return (FormaPagamento) broker.getObjectBean(FormaPagamento.class, "id", "descricao");
    }    

}
