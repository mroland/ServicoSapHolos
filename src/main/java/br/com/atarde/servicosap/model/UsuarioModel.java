package br.com.atarde.servicosap.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UsuarioModel implements Serializable {

	private Long id;

	private String login;

	private String senha;

	private String nome;

	private Boolean flagAtivo;

	public UsuarioModel() {
	}

	public UsuarioModel(String login, String senha) {

		this.login = login;

		this.senha = senha;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

}
