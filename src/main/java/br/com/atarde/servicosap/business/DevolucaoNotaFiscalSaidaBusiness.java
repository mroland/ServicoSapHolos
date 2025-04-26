package br.com.atarde.servicosap.business;

import com.google.gson.Gson;

import br.com.atarde.servicosap.dao.DevolucaoNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosap.sap.model.Status;
import br.com.atarde.servicosap.validation.DevolucaoNotaFiscalSaidaValidation;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class DevolucaoNotaFiscalSaidaBusiness extends MainBusiness<DevolucaoNotaFiscalSaida> {

	@Override
	public String validar(DevolucaoNotaFiscalSaida model) {

		StringBuilder retorno = new StringBuilder();
		
		model.setArquivoRemessa(new Gson().toJson(model));

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
