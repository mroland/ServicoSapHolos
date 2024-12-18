package br.com.atarde.servicosap.dao;

import java.io.Serializable;

import br.com.atarde.servicosap.model.UsuarioModel;
import br.com.atarde.servicosap.util.Constantes;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

@SuppressWarnings("serial")
public class UsuarioDAO implements Serializable {

	public UsuarioModel obter(UsuarioModel model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(Constantes.JNDI_SABWEB);

		broker.setSQL("SELECT ID FROM USUARIO U WHERE LOGIN = ? AND SENHA = ? AND FLAG_ATIVO ", model.getLogin(), model.getSenha());

		return (UsuarioModel) broker.getObjectBean(UsuarioModel.class, "id");

	}

}
