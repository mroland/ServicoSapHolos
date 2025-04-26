package br.com.atarde.servicosap.validation;

import java.util.List;

import br.com.atarde.servicosap.dao.EmpresaDAO;
import br.com.atarde.servicosap.model.TabelaUsuarioMovimentacao;
import br.com.atarde.servicosap.sap.dao.ItemDAO;
import br.com.atarde.servicosap.util.Constantes;
import br.com.topsys.util.TSUtil;

public class TabelaUsuarioMovimentacaoValidation extends DocumentoValidationAB {

	public String validarVenda(List<TabelaUsuarioMovimentacao> movimentacoes) {

		StringBuilder retorno = new StringBuilder();

		int contador = 1;
		for (TabelaUsuarioMovimentacao model : movimentacoes) {

			if (TSUtil.isEmpty(model.getEmpresa()) || (TSUtil.isEmpty(new EmpresaDAO().obter(model.getEmpresa())))) {

				retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

				retorno.append(this.validarFilial(model));

				if (TSUtil.isEmpty(model.getItem()) || (TSUtil.isEmpty(model.getItem().getId()))) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_MOVIMENTACOES_LINHA_ITEM + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

				} else {

					model.getItem().setEmpresa(model.getEmpresa());

					model.setItem(new ItemDAO().obter(model.getItem()));

					if (TSUtil.isEmpty(model.getItem())) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_MOVIMENTACOES_LINHA_ITEM + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

					}

				}

				if (TSUtil.isEmpty(model.getQuantidade()) || (model.getQuantidade() == 0)) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_MOVIMENTACOES_LINHA_QUANTIDADE + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

				}

				if (TSUtil.isEmpty(model.getIdExterno())) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_MOVIMENTACOES_ID_EXTERNO + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

				}

				if (TSUtil.isEmpty(model.getTipoMovimentacao()) || !"V".equals(model.getTipoMovimentacao())) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_MOVIMENTACOES_TIPO_MOVIMENTACAO + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

				}

			}

		}

		return retorno.toString();
	}

}
