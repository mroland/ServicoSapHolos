package br.com.atarde.servicosap.business;

import br.com.atarde.servicosap.dao.TransferenciaEstoqueDAO;
import br.com.atarde.servicosap.dao.VendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.model.TransferenciaEstoque;
import br.com.atarde.servicosap.sap.model.Status;
import br.com.atarde.servicosap.util.Constantes;
import br.com.atarde.servicosap.validation.TransferenciaEstoqueValidation;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class TransferenciaEstoqueBusiness extends MainBusiness<TransferenciaEstoque> {

	@Override
	public TransferenciaEstoque inserirLote(TransferenciaEstoque model) throws TSApplicationException {

		model.setStatus(new Status(0L));

		return new TransferenciaEstoqueDAO().inserirInterface(model);

	}

	@Override
	public String validar(TransferenciaEstoque model) {

		StringBuilder retorno = new StringBuilder();

		retorno.append(new TransferenciaEstoqueValidation().validar(model));

		if (!TSUtil.isEmpty(retorno.toString())) {

			model.setMensagemErro(retorno.toString());

		}

		return retorno.toString();

	}

}
