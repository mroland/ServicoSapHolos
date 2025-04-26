package br.com.atarde.servicosap.validation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import br.com.atarde.servicosap.sap.dao.ContaContabilDAO;
import br.com.atarde.servicosap.sap.dao.EstoqueDAO;
import br.com.atarde.servicosap.sap.dao.FilialDAO;
import br.com.atarde.servicosap.sap.dao.ItemDAO;
import br.com.atarde.servicosap.sap.dao.RegraDistribuicaoDAO;
import br.com.atarde.servicosap.sap.dao.UtilizacaoDAO;
import br.com.atarde.servicosap.sap.model.ContaContabil;
import br.com.atarde.servicosap.sap.model.DocumentoAB;
import br.com.atarde.servicosap.sap.model.DocumentoLinhaAB;
import br.com.atarde.servicosap.sap.model.Estoque;
import br.com.atarde.servicosap.sap.model.Filial;
import br.com.atarde.servicosap.sap.model.RegraDistribuicao;
import br.com.atarde.servicosap.sap.model.Utilizacao;
import br.com.atarde.servicosap.util.Constantes;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.topsys.util.TSUtil;

public class DocumentoValidationAB {

	public String validar(DocumentoAB model) {

		StringBuilder retorno = new StringBuilder();

		retorno.append(this.validarFilial(model));

		retorno.append(this.validarDocumento(model));

		return retorno.toString();

	}

	public String validarFilial(DocumentoAB model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model.getFilial()) || TSUtil.isEmpty(model.getFilial().getId())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_FILIAL + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.getFilial().setEmpresa(model.getEmpresa());

			if (TSUtil.isEmpty(new FilialDAO().obter(model.getFilial()))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_FILIAL + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

		}

		return retorno.toString();

	}

	public String validarDocumento(DocumentoAB model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model.getDataLancamento())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_LANCAMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getDataDocumento())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_DOCUMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getDataCriacao())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_DATA_CRIACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getDataExportacao())) {

			model.setDataExportacao(new Timestamp(System.currentTimeMillis()));

		}

		return retorno.toString();

	}

	protected String validaLinhaNFF(DocumentoLinhaAB model, Filial filial, int contador) {

		StringBuilder retorno = new StringBuilder();

		if ((!TSUtil.isEmpty(Utilitarios.tratarString(model.getContaContabil().getId())) && TSUtil.isEmpty(new ContaContabilDAO().obter(new ContaContabil(model.getContaContabil().getId(), model.getEmpresa()))))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_CONTA_CONTABIL + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getUnidadeNegocio()) || TSUtil.isEmpty(model.getUnidadeNegocio().getId()) || TSUtil.isEmpty(model.getUnidadeNegocio().getDimensao()) || TSUtil.isEmpty(model.getUnidadeNegocio().getDimensao().getId()) || (!TSUtil.isEmpty(Utilitarios.tratarString(model.getUnidadeNegocio().getId())) && TSUtil.isEmpty(new RegraDistribuicaoDAO().obter(new RegraDistribuicao(model.getUnidadeNegocio().getId(), model.getEmpresa(), model.getUnidadeNegocio().getDimensao()))))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_REGRA_DISTRIBUICAO_UNIDADE_NEGOCIO + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getItem()) || (TSUtil.isEmpty(model.getItem().getId()))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

		} else {

			model.getItem().setEmpresa(model.getEmpresa());

			model.setItem(new ItemDAO().obter(model.getItem()));

			if (TSUtil.isEmpty(model.getItem())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				model.getItem().setEmpresa(model.getEmpresa());

				if (model.getItem().getFlagControleEstoque()) {

					model.setEstoque(new Estoque());

					switch (filial.getId()) {

					case 1: // EDITORIAL

						model.getEstoque().setId("100");

						break;

					case 2: // RADIO

						model.getEstoque().setId("200");

						break;
					case 3: // ATN

						model.getEstoque().setId("300");

						break;

					}

					model.getItem().setEstoque(model.getEstoque());

					if (TSUtil.isEmpty(new EstoqueDAO().obterItemEstoque(new Estoque(model.getItem(), model.getEmpresa())))) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM_ESTOQUE_CONTROLE + " para o item " + model.getItem().getId() + " na linha " + contador + ". " + " \n");

					}

				} else if (model.getItem().getFlagItemVenda()) {

					model.setEstoque(new Estoque());

					switch (filial.getId()) {

					case 1: // EDITORIAL

						model.getEstoque().setId("199");

						break;

					case 2: // RADIO

						model.getEstoque().setId("299");

						break;
					case 3: // ATN

						model.getEstoque().setId("399");

						break;

					}

					if (TSUtil.isEmpty(new EstoqueDAO().obter(new Estoque(model.getEstoque().getId(), model.getEmpresa())))) {

						retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM_ESTOQUE + " na linha " + contador + ". " + "\n");

					}

				} else {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_TIPO_ITEM_OBRIGATORIO + " na linha " + contador + ". " + "\n");

				}

			}

		}

		if (TSUtil.isEmpty(model.getQuantidade()) || (model.getQuantidade() == 0)) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_QUANTIDADE + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getValor()) || model.getValor().setScale(2, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)) != 1) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_VALOR + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getUtilizacao()) || TSUtil.isEmpty(Utilitarios.tratarLong(model.getUtilizacao().getId())) || (!TSUtil.isEmpty(Utilitarios.tratarLong(model.getUtilizacao().getId())) && TSUtil.isEmpty(new UtilizacaoDAO().obter(new Utilizacao(model.getUtilizacao().getId(), model.getEmpresa()))))) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_UTILIZACAO + " na linha " + contador + ". " + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		return retorno.toString();
	}

}
