package br.com.atarde.servicosap.business;

import br.com.atarde.servicosap.dao.DevolucaoNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.Status;
import br.com.atarde.servicosap.validation.DevolucaoNotaFiscalSaidaValidation;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class DevolucaoNotaFiscalSaidaBusiness extends MainBusiness<DevolucaoNotaFiscalSaida> {

	@Override
	public void inserirSAP(Empresa empresa) {

	}

	@Override
	public void alterarStatusInterface() throws TSApplicationException {
		// TODO Auto-generated method stub

	}

	@Override
	public DevolucaoNotaFiscalSaida inserir(DevolucaoNotaFiscalSaida model) {

		return null;

	}

	@Override
	public String validar(DevolucaoNotaFiscalSaida model) {

		StringBuilder retorno = new StringBuilder();

		retorno.append(new DevolucaoNotaFiscalSaidaValidation().validar(model));

		if (!TSUtil.isEmpty(retorno.toString())) {

			model.setMensagemErro(retorno.toString());

		}

		return retorno.toString();

	}

	@Override
	public DevolucaoNotaFiscalSaida inserirLote(DevolucaoNotaFiscalSaida model) throws TSApplicationException {

		model.setStatus(new Status(0L));

		return new DevolucaoNotaFiscalSaidaDAO().inserirInterface(model);

	}

}
