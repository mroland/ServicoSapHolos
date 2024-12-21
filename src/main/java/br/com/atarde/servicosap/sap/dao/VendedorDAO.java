/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.Vendedor;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class VendedorDAO {

	public Vendedor obterPeloIdentificador(Vendedor model) {

		TSDataBaseBrokerIf brokerIf = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		brokerIf.setSQL("SELECT OSLP.\"SlpCode\" CODE FROM " + model.getEmpresa().getDbInstancia() + ".OSLP WHERE OSLP.\"Memo\"= ? AND OSLP.\"Active\" = 'Y'", model.getIdentificador());

		return (Vendedor) brokerIf.getObjectBean(Vendedor.class, "id");

	}

	public Vendedor obterPeloNome(Vendedor model) {

		TSDataBaseBrokerIf brokerIf = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		brokerIf.setSQL("SELECT OSLP.\"SlpCode\" CODE FROM " + model.getEmpresa().getDbInstancia() + ".OSLP WHERE OSLP.\"SlpName\" = ? AND OSLP.\"Active\" = 'Y'", model.getNome());

		return (Vendedor) brokerIf.getObjectBean(Vendedor.class, "id");

	}

	public Vendedor obter(Vendedor model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OSLP.\"SlpCode\" AS ID FROM " + model.getEmpresa().getDbInstancia() + ".OSLP WHERE OSLP.\"SlpCode\" = ?", model.getId());

		return (Vendedor) broker.getObjectBean(Vendedor.class, "id");

	} 

}
