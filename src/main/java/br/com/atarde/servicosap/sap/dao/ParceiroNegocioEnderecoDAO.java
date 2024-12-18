/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.sap.dao;

import java.util.List;

import br.com.atarde.servicosap.sap.model.ParceiroNegocio;
import br.com.atarde.servicosap.sap.model.ParceiroNegocioEndereco;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class ParceiroNegocioEnderecoDAO {

    public ParceiroNegocioEnderecoDAO() {
    }

	@SuppressWarnings("unchecked")
	public List<ParceiroNegocioEndereco> pesquisar(ParceiroNegocio model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("parceironegocioenderecodao.pesquisar", model.getId());

        return broker.getCollectionBean(ParceiroNegocioEndereco.class, "id");

	}

    public ParceiroNegocioEndereco obter(ParceiroNegocioEndereco model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("parceironegocioenderecodao.obter", model.getParceiroNegocio().getId(), model.getTipoEndereco(), model.getId());

        return (ParceiroNegocioEndereco) broker.getObjectBean(ParceiroNegocioEndereco.class, "id");
    }

}
