/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.IdentificadorFiscal;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class IdentificadorFiscalDAO {

    public IdentificadorFiscalDAO() {
    }

    public IdentificadorFiscal obterEnderecoNulo(IdentificadorFiscal model){

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("identificadorfiscalsapdao.obterendereconulo", model.getParceiroNegocio().getId(),model.getEnderecoId());

        return (IdentificadorFiscal) broker.getObjectBean(IdentificadorFiscal.class, "enderecoId");
        
    }

}
