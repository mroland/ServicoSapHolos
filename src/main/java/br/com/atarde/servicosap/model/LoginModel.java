package br.com.atarde.servicosap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginModel implements Serializable {

	private String login;
	private String senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
