/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Municipio;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class MunicipioDAO {

    public Municipio obter(Municipio model){

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("municipiodao.obter", model.getEnderecoAB().getPais().getId(), model.getEnderecoAB().getEstado().getId(), model.getEnderecoAB().getCidade());

        return (Municipio) broker.getObjectBean(Municipio.class, "id");
    }

}
