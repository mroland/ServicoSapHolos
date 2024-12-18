package br.com.atarde.servicosap.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.atarde.servicosap.business.EasyclassNotaFiscalSaidaBusiness;
import br.com.atarde.servicosap.dao.EmpresaDAO;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;

/**
 * Servlet implementation class NotaFiscalSaidaServlet
 */
@WebServlet("/EasyclassNotaFiscalSaidaServlet")
public class EasyclassNotaFiscalSaidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EasyclassNotaFiscalSaidaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.processar(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.processar(request, response);

	}

	private void processar(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String empresaId = (String) request.getParameter("empresaId");

		if (TSUtil.isEmpty(empresaId)) {

			response.getWriter().print("Favor inserir uma empresaId");

		} else {

			Empresa empresa = new EmpresaDAO().obter(new Empresa(TSParseUtil.stringToLong(empresaId)));

			if (TSUtil.isEmpty(empresa)) {

				response.getWriter().print("Favor inserir uma empresaId válida.");

			} else {

				new EasyclassNotaFiscalSaidaBusiness().inserirSAP(empresa);

			}

		}

	}

}
