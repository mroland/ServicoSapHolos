package br.com.atarde.servicosap.validation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.atarde.servicosap.dao.VendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.model.TabelaUsuarioMovimentacao;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.sap.dao.ContaContabilDAO;
import br.com.atarde.servicosap.sap.dao.EstoqueDAO;
import br.com.atarde.servicosap.sap.dao.ItemDAO;
import br.com.atarde.servicosap.sap.dao.RegraDistribuicaoDAO;
import br.com.atarde.servicosap.sap.dao.UtilizacaoDAO;
import br.com.atarde.servicosap.sap.model.CFOP;
import br.com.atarde.servicosap.sap.model.CST;
import br.com.atarde.servicosap.sap.model.CodigoImposto;
import br.com.atarde.servicosap.sap.model.ContaContabil;
import br.com.atarde.servicosap.sap.model.Estoque;
import br.com.atarde.servicosap.sap.model.Filial;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosap.sap.model.PedidoVenda;
import br.com.atarde.servicosap.sap.model.PedidoVendaLinha;
import br.com.atarde.servicosap.sap.model.RegraDistribuicao;
import br.com.atarde.servicosap.sap.model.Utilizacao;
import br.com.atarde.servicosap.util.Constantes;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.topsys.util.TSUtil;

public class VendaAvulsaNotaFiscalSaidaValidation extends NotaFiscalSaidaValidation {

	public String validar(VendaAvulsaNotaFiscalSaida model) {

		StringBuilder retorno = new StringBuilder();

		retorno.append(super.validar(model));

		if (TSUtil.isEmpty(retorno.toString())) {

			retorno.append(this.validaNFF(model));

		}

		return retorno.toString();

	}

	public String validaNFF(NotaFiscalSaidaAB model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(retorno.toString())) {

			VendaAvulsaNotaFiscalSaida nota = (VendaAvulsaNotaFiscalSaida) model;

			if (!TSUtil.isEmpty(new VendaAvulsaNotaFiscalSaidaDAO().obterIdExternoInterface(nota))) {

				retorno.append(Constantes.DOCUMENTOEXPORTADO + "\n");

				return retorno.toString();

			}

			if (TSUtil.isEmpty(nota.getFlagConsignado())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NFF_SAIDA_FLAG_CONSIGNADO + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				if (Constantes.FILIAL_JORNAL.equals(nota.getFilial().getId()) && nota.getFlagConsignado() && !TSUtil.isEmpty(model.getSequencia()) && !Constantes.SEQUENCIA_NFF_FATURA.equals(model.getSequencia().getId())) {

					retorno.append(Constantes.OBJETO_SEQUENCIA_FATURA_FLAG_CONSIGNADO + "\n");

				}

				if (!nota.getFlagConsignado() && (TSUtil.isEmpty(nota.getMovimentacoes()) || nota.getMovimentacoes().size() == 0)) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_NFF_SAIDA_MOVIMENTACOES + Constantes.CAMPO_OBRIGATORIO + "\n");

				}

			}

			if (TSUtil.isEmpty(Utilitarios.tratarString(nota.getCliente().getId()))) {

				if ((nota.getCliente().getIdentificadorFiscal().getTipoIdentificador() == 0) && (TSUtil.isEmpty(nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual()) || (nota.getCliente().getIdentificadorFiscal().getTipoIdentificador() == 0 && (!TSUtil.isEmpty(nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual())) && nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual().length() > 15))) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL + Constantes.CAMPO_OBRIGATORIO + "\n");

				}

			}

			if (TSUtil.isEmpty(nota.getObservacao()) || (!TSUtil.isEmpty(nota.getObservacao()) && nota.getObservacao().length() > 500)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setUObservacao(nota.getUObservacao().toUpperCase());
			}

			if ((nota.getSequencia().getId() == 28) && (TSUtil.isEmpty(nota.getUObservacao()) || (!TSUtil.isEmpty(nota.getUObservacao()) && nota.getUObservacao().length() > 500))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(nota.getUBanca()) || (!TSUtil.isEmpty(nota.getUBanca()) && nota.getUBanca().length() > 100)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_BANCA + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setUBanca(nota.getUBanca().toUpperCase());
			}

			if (TSUtil.isEmpty(nota.getULote()) || (!TSUtil.isEmpty(nota.getULote()) && nota.getULote().length() > 50)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_LOTE + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(nota.getUTipoBanca()) || (!TSUtil.isEmpty(nota.getUTipoBanca()) && nota.getUTipoBanca().length() > 50)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_TIPOBANCA + Constantes.CAMPO_OBRIGATORIO + "\n");
			} else {

				nota.setUTipoBanca(nota.getUTipoBanca().toUpperCase());
			}

			if (TSUtil.isEmpty(nota.getUTipoFaturamento()) || (!TSUtil.isEmpty(nota.getUTipoFaturamento()) && nota.getUTipoFaturamento().length() > 50)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_TIPOFATURAMENTO + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setUTipoFaturamento(nota.getUTipoFaturamento().toUpperCase());

			}

			if (TSUtil.isEmpty(nota.getUEnderecoEntrega()) || (!TSUtil.isEmpty(nota.getUEnderecoEntrega()) && nota.getUEnderecoEntrega().length() > 254)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_ENDERECO_ENTREGA + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setUEnderecoEntrega(nota.getUEnderecoEntrega().toUpperCase());
			}

			if (TSUtil.isEmpty(nota.getUObservacao()) || (!TSUtil.isEmpty(nota.getUObservacao()) && nota.getUObservacao().length() > 254)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setUObservacao(nota.getUObservacao().toUpperCase());
			}

			if (!TSUtil.isEmpty(nota.getLinhas()) || (!TSUtil.isEmpty(nota.getLinhas()) && nota.getLinhas().size() != 0)) {

				int contador = 1;
				for (VendaAvulsaNotaFiscalSaidaLinha linha : nota.getLinhas()) {

					linha.setEmpresa(model.getEmpresa());

					// colocar fixo para que nao impacte os sistemas legados de implementar
					linha.setCodigoImposto(new CodigoImposto());
					linha.setContaContabil(new ContaContabil());

					// linha.getCodigoImposto().setId("5101-006");

					// linha.setUtilizacao(new Utilizacao());

					// linha.getUtilizacao().setId(9L);

					linha.setCfop(new CFOP());

					linha.setCstIPI(new CST());

					linha.setCstPIS(new CST());

					linha.setCstCOFINS(new CST());

					linha.setCstICMS(new CST());

					retorno.append(this.validaLinhaNFF(linha, model.getFilial(), contador, nota.getFlagConsignado()));

					contador++;

				}

				if (TSUtil.isEmpty(retorno.toString()) && !TSUtil.isEmpty(nota.getMovimentacoes())) {

					retorno.append(new TabelaUsuarioMovimentacaoValidation().validarVenda(nota.getMovimentacoes(), "V"));

				}

				if (TSUtil.isEmpty(retorno.toString()) && !TSUtil.isEmpty(nota.getMovimentacoes())) {

					retorno.append(this.validaMovimentacoes(nota.getLinhas(), nota.getMovimentacoes()));

				}

			} else {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_LISTA_DOCUMENTOAB_LINHA + "\n");

			}

		}

		return retorno.toString();

	}

	private String validaMovimentacoes(List<VendaAvulsaNotaFiscalSaidaLinha> linhas, List<TabelaUsuarioMovimentacao> movimentacoes) {

		StringBuilder retorno = new StringBuilder();

		Map<String, Double> mapaLinha = new HashMap<String, Double>();

		for (VendaAvulsaNotaFiscalSaidaLinha item : linhas) {

			if (!mapaLinha.containsKey(item.getItem().getId())) {

				mapaLinha.put(item.getItem().getId(), item.getQuantidade());

			} else {

				mapaLinha.put(item.getItem().getId(), mapaLinha.get(item.getItem().getId()) + item.getQuantidade());

			}

		}

		Map<String, Double> mapaMovimentacao = new HashMap<String, Double>();

		for (TabelaUsuarioMovimentacao item : movimentacoes) {

			if (!mapaMovimentacao.containsKey(item.getItem().getId())) {

				mapaMovimentacao.put(item.getItem().getId(), item.getQuantidade());

			} else {

				mapaMovimentacao.put(item.getItem().getId(), mapaMovimentacao.get(item.getItem().getId()) + item.getQuantidade());

			}

		}

		retorno.append(this.validarMovimentacoes(mapaLinha, mapaMovimentacao));

		retorno.append(this.validarMovimentacoes(mapaMovimentacao, mapaLinha));

		return retorno.toString();

	}

	private String validarMovimentacoes(Map<String, Double> mapaOrigem, Map<String, Double> mapaDestino) {

		StringBuilder retorno = new StringBuilder();

		for (Map.Entry<String, Double> entry : mapaOrigem.entrySet()) {

			if (!mapaDestino.containsKey(entry.getKey())) {

				retorno.append("Favor inserir em nota.movimentações e nota.linhas o item.id " + entry.getKey() + ". \n");

			} else {

				if (mapaDestino.get(entry.getKey()).doubleValue() != entry.getValue().doubleValue()) {

					retorno.append("Favor inserir a mesma quantidade do item.id " + entry.getKey() + " tanto para nota.movimentações quanto para nota.linhas" + ". \n");

				}

			}

		}

		return retorno.toString();

	}

	protected String validaLinhaNFF(VendaAvulsaNotaFiscalSaidaLinha model, Filial filial, int contador, Boolean flagConsignado) {

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

				if (TSUtil.isEmpty(model.getEstoque()) || TSUtil.isEmpty(new EstoqueDAO().obter(new Estoque(model.getEstoque().getId(), model.getEmpresa())))) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_LINHA_ITEM_ESTOQUE + " na linha " + contador + ". " + "\n");

				}

				if (!model.getItem().getFlagControleEstoque() && !model.getItem().getFlagItemVenda()) {

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

		if (!TSUtil.isEmpty(flagConsignado) && flagConsignado) {

			if (Constantes.FILIAL_JORNAL.equals(filial.getId())) {

				if (!Constantes.UTILIZACAO_NFF_SAIDA_REMESSA_CONSIGNADO.equals(model.getUtilizacao().getId())) {

					retorno.append(Constantes.OBJETO_NOTAFISCALSAIDA_LINHA_UTILIZACAO_REMESSA_CONSIGNADO + " na linha " + contador + ". " + "\n");

				}

				if (!TSUtil.isEmpty(model.getEstoque()) && !TSUtil.isEmpty(model.getEstoque().getId()) && !Constantes.DEPOSITO_SEM_ESTOQUE.equals(model.getEstoque().getId())) {

					retorno.append(Constantes.OBJETO_NOTAFISCALSAIDA_LINHA_DEPOSITO_CONSIGNADO + " na linha " + contador + ". " + "\n");

				}

			}

		}

		if (!TSUtil.isEmpty(model.getPedidoVendaLinha())) {

			if (TSUtil.isEmpty(model.getPedidoVendaLinha().getNumero())) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_PEDIDO_VENDA_LINHA_NUMERO);

			}

			if (TSUtil.isEmpty(model.getPedidoVendaLinha().getPedidoVenda()) || (TSUtil.isEmpty(Utilitarios.tratarLong(model.getPedidoVendaLinha().getPedidoVenda().getId())))) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_PEDIDO_VENDA);

			}

		} else {

			model.setPedidoVendaLinha(new PedidoVendaLinha());

			model.getPedidoVendaLinha().setPedidoVenda(new PedidoVenda());

		}

		return retorno.toString();

	}

}
