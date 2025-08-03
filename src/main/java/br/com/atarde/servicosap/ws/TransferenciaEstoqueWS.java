package br.com.atarde.servicosap.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.atarde.servicosap.business.TransferenciaEstoqueBusiness;
import br.com.atarde.servicosap.model.TransferenciaEstoque;
import br.com.atarde.servicosap.util.TokenService;
import br.com.topsys.exception.TSApplicationException;

@Path(value = "/transferenciaEstoqueWS")
public class TransferenciaEstoqueWS {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path(value = "/inserirLote")
	public Response inserirLote(@HeaderParam("Authorization") String token, TransferenciaEstoque model) {

		// Verifica se o token foi passado no cabeçalho e remove o prefixo "Bearer "
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7); // Remove "Bearer "

			// Valida o token JWT
			if (TokenService.validateToken(token)) {

				try {

					TransferenciaEstoque conta = new TransferenciaEstoqueBusiness().insertBatchEvent(model);

					return Response.ok(conta).build();

				} catch (TSApplicationException e) {

					e.printStackTrace();

					model.setMensagemErro(e.getMessage());

				}

				return Response.ok(model).build();

			} else {

				return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();

			}

		} else {

			return Response.status(Response.Status.UNAUTHORIZED).entity("Token ausente").build();

		}

	}

}
