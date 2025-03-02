package br.com.atarde.servicosap.validation;

import br.com.atarde.servicosap.dao.AssinaturaNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.sap.model.CFOP;
import br.com.atarde.servicosap.sap.model.CST;
import br.com.atarde.servicosap.sap.model.CodigoImposto;
import br.com.atarde.servicosap.sap.model.ContaContabil;
import br.com.atarde.servicosap.sap.model.Estoque;
import br.com.atarde.servicosap.sap.model.Filial;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosap.sap.model.PedidoVenda;
import br.com.atarde.servicosap.sap.model.PedidoVendaLinha;
import br.com.atarde.servicosap.util.Constantes;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.topsys.util.TSUtil;

public class AssinaturaNotaFiscalSaidaValidation extends NotaFiscalSaidaValidation {

	public String validar(AssinaturaNotaFiscalSaida model) {

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

			AssinaturaNotaFiscalSaida nota = (AssinaturaNotaFiscalSaida) model;

			if (!TSUtil.isEmpty(new AssinaturaNotaFiscalSaidaDAO().obterIdExternoInterface(nota))) {

				retorno.append(Constantes.DOCUMENTOEXPORTADO + "\n");

				return retorno.toString();

			}

			if (TSUtil.isEmpty(Utilitarios.tratarString(nota.getCliente().getId()))) {

				if ((nota.getCliente().getIdentificadorFiscal().getTipoIdentificador() == 0) && (TSUtil.isEmpty(nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual()) || (!TSUtil.isEmpty(nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual()) && nota.getCliente().getIdentificadorFiscal().getInscricaoEstadual().length() > 15))) {

					retorno.append(Constantes.OBJETO_OBRIGATORIO_PARCEIRO_NEGOCIO_INSCRICAO_ESTADUAL + Constantes.CAMPO_OBRIGATORIO + "\n");

				}

			}

			if (TSUtil.isEmpty(nota.getObservacao()) || nota.getObservacao().length() > 254) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setObservacao(model.getObservacao().toUpperCase());

			}

			if (TSUtil.isEmpty(nota.getUObservacao()) || nota.getUObservacao().length() > 254) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_OBSERVACAO + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setUObservacao(nota.getUObservacao().toUpperCase());

			}

			if (!TSUtil.isEmpty(nota.getUTermo()) && nota.getUTermo().length() > 254) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_TERMO + Constantes.CAMPO_OBRIGATORIO + "\n");

			}

			if (TSUtil.isEmpty(nota.getUEnderecoEntrega()) || (!TSUtil.isEmpty(nota.getUEnderecoEntrega()) && nota.getUEnderecoEntrega().length() > 254)) {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCALSAIDA_U_ENDERECO_ENTREGA + Constantes.CAMPO_OBRIGATORIO + "\n");

			} else {

				nota.setUEnderecoEntrega(nota.getUEnderecoEntrega().toUpperCase());
			}

			if (!TSUtil.isEmpty(nota.getLinhas()) || (!TSUtil.isEmpty(nota.getLinhas()) && nota.getLinhas().size() != 0)) {

				int contador=1;
				for (AssinaturaNotaFiscalSaidaLinha linha : nota.getLinhas()) {

					linha.setEmpresa(model.getEmpresa());

					// colocar fixo para que nao impacte os sistemas legados de implementar
					linha.setCodigoImposto(new CodigoImposto());
					linha.setContaContabil(new ContaContabil());

					// linha.getCodigoImposto().setId("5101-006");

					//linha.setUtilizacao(new Utilizacao());

					//linha.getUtilizacao().setId(9L);

					linha.setCfop(new CFOP());

					linha.setCstIPI(new CST());

					linha.setCstPIS(new CST());

					linha.setCstCOFINS(new CST());

					linha.setCstICMS(new CST());

					linha.setEstoque(new Estoque());

					retorno.append(this.validaLinhaNFF(linha, model.getFilial(), contador));
					
					contador++;

				}

			} else {

				retorno.append(Constantes.OBJETO_OBRIGATORIO_LISTA_DOCUMENTOAB_LINHA + "\n");

			}

		}

		return retorno.toString();

	}

	protected String validaLinhaNFF2(AssinaturaNotaFiscalSaidaLinha model, Filial filial, int contador) {

		StringBuilder retorno = new StringBuilder();

		retorno.append(super.validaLinhaNFF(model, filial, contador));

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
