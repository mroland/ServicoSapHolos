/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Classificacao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class ClassificacaoDAO {

    public ClassificacaoDAO() {
    }

    public Classificacao obter(Classificacao model){

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("classificacaodao.obter", model.getId(), model.getParceiroNegocio().getTipo());

        return (Classificacao) broker.getObjectBean(Classificacao.class, "id");


    }

}
