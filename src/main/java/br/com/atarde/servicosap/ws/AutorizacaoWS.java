package br.com.atarde.servicosap.ws;

import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.atarde.servicosap.dao.UsuarioDAO;
import br.com.atarde.servicosap.model.LoginModel;
import br.com.atarde.servicosap.model.UsuarioModel;
import br.com.atarde.servicosap.util.TokenService;
import br.com.topsys.util.TSUtil;

@Path("/login")
public class AutorizacaoWS {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginModel model) {

		if (this.validarAutenticacao(model)) {

			// Gera um token JWT
			String token = TokenService.generateToken(model.getLogin());
			
			Calendar c = Calendar.getInstance();
			
			c.setTimeInMillis(c.getTimeInMillis() + TokenService.EXPIRATION_TIME);

			// Retorna o token no corpo da resposta
			return Response.ok("{\"token\":\"" + token + "\", \"expiration\":\"" + c.getTimeInMillis()  + "\"}").build();

		}

		return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas").build();

	}

	private boolean validarAutenticacao(LoginModel model) {

		Boolean retorno = true;

		if (TSUtil.isEmpty(model) || TSUtil.isEmpty(model.getLogin()) || TSUtil.isEmpty(model.getSenha()) || TSUtil.isEmpty(new UsuarioDAO().obter(new UsuarioModel(model.getLogin(), model.getSenha())))) {

			retorno = false;

		}

		return retorno;
	}
	
}
