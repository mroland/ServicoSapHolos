package br.com.atarde.servicosap.validation;

import br.com.atarde.servicosap.dao.EmpresaDAO;
import br.com.atarde.servicosap.model.TransferenciaEstoque;
import br.com.atarde.servicosap.model.TransferenciaEstoqueLinha;
import br.com.atarde.servicosap.sap.dao.EstoqueDAO;
import br.com.atarde.servicosap.sap.dao.ItemDAO;
import br.com.atarde.servicosap.sap.dao.OrigemDAO;
import br.com.atarde.servicosap.sap.model.Estoque;
import br.com.atarde.servicosap.sap.model.Origem;
import br.com.atarde.servicosap.util.Constantes;
import br.com.topsys.util.TSUtil;

public class TransferenciaEstoqueValidation extends DocumentoValidationAB {

	public String validar(TransferenciaEstoque model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model)) {

			retorno.append(Constantes.TRANSFERENCIA_ESTOQUE + "\n");

		} else {

			if (TSUtil.isEmpty(model.getEmpresa()) || (TSUtil.isEmpty(new EmpresaDAO().obter(model.getEmpresa())))) {

				retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO);

			} else {

				model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

				if (TSUtil.isEmpty(model.getIdExterno())) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_ID_EXTERNO + Constantes.CAMPO_OBRIGATORIO + "\n");

				} else {

					retorno.append(super.validar(model));

					if (TSUtil.isEmpty(model.getObservacaoDiario()) || (model.getObservacaoDiario().length() > 254)) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_OBSERVACAO_DIARIO_ID_EXTERNO + Constantes.CAMPO_OBRIGATORIO + "\n");

					}

					if (TSUtil.isEmpty(model.getEstoqueOrigem()) || (TSUtil.isEmpty(new EstoqueDAO().obter(new Estoque(model.getEstoqueOrigem().getId(), model.getEmpresa()))))) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_ESTOQUE_ORIGEM + Constantes.CAMPO_OBRIGATORIO + "\n");

					}

					if (TSUtil.isEmpty(model.getEstoqueDestino()) || (TSUtil.isEmpty(new EstoqueDAO().obter(new Estoque(model.getEstoqueDestino().getId(), model.getEmpresa()))))) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_ESTOQUE_DESTINO + Constantes.CAMPO_OBRIGATORIO + "\n");

					}

					if (!TSUtil.isEmpty(model.getEstoqueDestino()) && !TSUtil.isEmpty(model.getEstoqueOrigem()) && model.getEstoqueOrigem().getId().equals(model.getEstoqueDestino().getId())) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_ESTOQUE_IGUAL + "\n");

					}

					if (TSUtil.isEmpty(model.getOrigem()) || TSUtil.isEmpty(new OrigemDAO().obter(new Origem(model.getEmpresa(), model.getOrigem().getId())))) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_ORIGEM + Constantes.CAMPO_OBRIGATORIO + "\n");

					}

				}

				if (TSUtil.isEmpty(model.getLinhas())) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_LINHA + Constantes.CAMPO_OBRIGATORIO + "\n");

				} else {

					int contador = 1;
					for (TransferenciaEstoqueLinha linha : model.getLinhas()) {

						linha.setEmpresa(model.getEmpresa());

						retorno.append(this.validarLinha(linha, contador));

						contador++;

					}

				}

			}

		}

		return retorno.toString();

	}

	private String validarLinha(TransferenciaEstoqueLinha model, int contador) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model.getItem()) || (TSUtil.isEmpty(model.getItem().getId()))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_LINHA_ITEM + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.getItem().setEmpresa(model.getEmpresa());

			model.setItem(new ItemDAO().obter(model.getItem()));

			if (TSUtil.isEmpty(model.getItem())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_LINHA_ITEM + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		}

		if (TSUtil.isEmpty(model.getQuantidade()) || (model.getQuantidade() == 0)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_TRANSFERENCIA_ESTOQUE_LINHA_ITEM + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		return retorno.toString();

	}

}
