package br.com.atarde.servicosap.business;

import br.com.atarde.servicosap.sap.dao.VendedorDAO;
import br.com.atarde.servicosap.sap.diapi.VendedorSapDiApiDAO;
import br.com.atarde.servicosap.sap.model.Vendedor;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class VendedorBusiness {

	public Vendedor validar(Vendedor model) throws TSApplicationException {

		if (TSUtil.isEmpty(Utilitarios.tratarLong(model.getId()))) {
			
			Vendedor vendedor = new VendedorDAO().obterPeloIdentificador(model);

			if (TSUtil.isEmpty(vendedor)) {

				if (TSUtil.isEmpty(new VendedorDAO().obterPeloNome(model))) {

					model = new VendedorSapDiApiDAO().inserir(model);

				} else {

					model.setMensagemErro("Erro: Vendedor nome=" + model.getNome().toString() + " igual da base mas de id(" + model.getId().toString() + ") diferente");

					throw new TSApplicationException("Erro: Vendedor nome=" + model.getNome().toString() + " igual da base mas de id(" + model.getId().toString() + ") diferente");

				}

			}else{
				
				model.setId(vendedor.getId());
				
			}

		}

		return model;

	}

}
