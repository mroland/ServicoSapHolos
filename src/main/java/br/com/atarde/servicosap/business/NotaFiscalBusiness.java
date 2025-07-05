package br.com.atarde.servicosap.business;

import com.google.gson.Gson;

import br.com.atarde.servicosap.dao.AssinaturaNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.dao.DevolucaoNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.dao.EasyclassNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.dao.EmpresaDAO;
import br.com.atarde.servicosap.dao.VendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosap.model.ClassificadosContratoNotaFiscalSaida;
import br.com.atarde.servicosap.model.DevolucaoNotaFiscalSaida;
import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.sap.dao.NotaFiscalSaidaDAO;
import br.com.atarde.servicosap.sap.dao.OrigemDAO;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosap.sap.model.Origem;
import br.com.atarde.servicosap.sap.model.Status;
import br.com.atarde.servicosap.util.Constantes;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.atarde.servicosap.validation.AssinaturaNotaFiscalSaidaValidation;
import br.com.atarde.servicosap.validation.ClassificadosContratoNotaFiscalSaidaValidation;
import br.com.atarde.servicosap.validation.DocumentoValidationAB;
import br.com.atarde.servicosap.validation.EasyclassNotaFiscalSaidaValidation;
import br.com.atarde.servicosap.validation.VendaAvulsaNotaFiscalSaidaValidation;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class NotaFiscalBusiness extends MainBusiness<NotaFiscalSaidaAB> {

	@Override
	public NotaFiscalSaidaAB inserirLote(NotaFiscalSaidaAB model) throws TSApplicationException {

		model.setStatus(new Status(0L));

		switch (model.getOrigem().getId().intValue()) {

		case 1:// Easyclass

			if (model instanceof EasyclassNotaFiscalSaida) {

				return new EasyclassNotaFiscalSaidaDAO().inserirInterface((EasyclassNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia EasyclassNotaFiscalSaida.");

			}

			break;
		case 2: // VendaAvulsa

			if (model instanceof VendaAvulsaNotaFiscalSaida) {

				return new VendaAvulsaNotaFiscalSaidaDAO().inserirInterface((VendaAvulsaNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia VendaAvulsaNotaFiscalSaida.");

			}

			break;

		case 3: // Assinatura

			if (model instanceof AssinaturaNotaFiscalSaida) {

				return new AssinaturaNotaFiscalSaidaDAO().inserirInterface((AssinaturaNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia AssinaturaNotaFiscalSaida.");

			}

			break;

		case 4: // Fotografia

			model.setMensagemErro("Ainda nao implementado Fotografia para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 5: // AtardeOnline

			model.setMensagemErro("Ainda nao implementado AtardeOnline para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 6: // SVG

			model.setMensagemErro("Ainda nao implementado SVG para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 7: // SAP

			model.setMensagemErro("Ainda nao implementado SAP para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 8: // Classificados

			model.setMensagemErro("Ainda nao implementado Classificados para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 9: // Promedica

			model.setMensagemErro("Ainda nao implementado Promedica para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 10: // Confissao Dividas

			model.setMensagemErro("Ainda nao implementado Confissao Dividas para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 11:// Cheque Devolvido

			model.setMensagemErro("Ainda nao implementado Cheque Devolvido para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		case 12: // Publicidade Online

			model.setMensagemErro("Ainda nao implementado Publicidade Online para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		default:

			model.setMensagemErro("Ainda nao implementado para a empresaId:" + model.getEmpresa().getId().toString());

			break;

		}

		return model;

	}

	@Override
	public String validar(NotaFiscalSaidaAB model) {

		StringBuilder retorno = new StringBuilder();

		if (!TSUtil.isEmpty(model) && !TSUtil.isEmpty(model.getOrigem()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getOrigem().getId()))) {

			model.setArquivoRemessa(new Gson().toJson(model));

			model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

			if (!TSUtil.isEmpty(model.getEmpresa()) && !TSUtil.isEmpty(new OrigemDAO().obter(new Origem(model.getEmpresa(), model.getOrigem().getId())))) {

				switch (model.getOrigem().getId().intValue()) {

				case 1:// Easyclass

					if (model instanceof EasyclassNotaFiscalSaida) {

						retorno.append(new EasyclassNotaFiscalSaidaValidation().validar((EasyclassNotaFiscalSaida) model));

					} else {

						retorno.append("Objeto nao instanciado como Easyclass. ");

					}

					break;

				case 2: // VendaAvulsa

					if (model instanceof VendaAvulsaNotaFiscalSaida) {

						retorno.append(new VendaAvulsaNotaFiscalSaidaValidation().validar((VendaAvulsaNotaFiscalSaida) model));

					} else {

						retorno.append("Objeto nao instanciado como VendaAvulsaNotaFiscalSaida. ");

					}

					break;

				case 3: // Assinatura

					if (model instanceof AssinaturaNotaFiscalSaida) {

						retorno.append(new AssinaturaNotaFiscalSaidaValidation().validar((AssinaturaNotaFiscalSaida) model));

					} else {

						retorno.append("Objeto nao instanciado como AssinaturaNotaFiscalSaida. ");

					}

					break;

				case 4: // Fotografia

					retorno.append("Ainda nao implementado Fotografia para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 5: // AtardeOnline

					retorno.append("Ainda nao implementado AtardeOnline para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 6: // SVG

					retorno.append("Ainda nao implementado SVG para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 7: // SAP

					retorno.append("Ainda nao implementado SAP para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 8: // Classificados

					if (model instanceof ClassificadosContratoNotaFiscalSaida) {

						retorno.append(new ClassificadosContratoNotaFiscalSaidaValidation().validar((ClassificadosContratoNotaFiscalSaida) model));

					} else {

						retorno.append("Objeto nao instanciado como ClassificadosContratoNotaFiscalSaida. ");

					}

					break;

				case 9: // Promedica

					retorno.append("Ainda nao implementado Promedica para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 10: // Confissao Dividas

					retorno.append("Ainda nao implementado Confissao Dividas para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 11:// Cheque Devolvido

					retorno.append("Ainda nao implementado Cheque Devolvido para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				case 12: // Publicidade Online

					retorno.append("Ainda nao implementado Publicidade Online para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				default:

					retorno.append("Ainda nao implementado para a empresaId:" + model.getEmpresa().getId().toString());

					break;

				}

			} else {

				retorno.append("Favor setar objeto NotaFiscalSaidaAB.origem.id válido para a empresaId:" + model.getEmpresa().getId().toString());

			}

		} else {

			retorno.append("Favor setar objeto NotaFiscalSaidaAB.origem.id válido para a empresaId:" + model.getEmpresa().getId().toString());

		}

		if (!TSUtil.isEmpty(retorno.toString())) {

			model.setMensagemErro(retorno.toString());

		}

		return retorno.toString();

	}

	public NotaFiscalSaidaAB obterPorIdExterno(NotaFiscalSaidaAB model) {

		StringBuilder retorno = new StringBuilder();

		if (TSUtil.isEmpty(model.getIdExterno())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_NOTAFISCAL_ID_EXTERNO + Constantes.CAMPO_OBRIGATORIO + "\n");

		}

		if (TSUtil.isEmpty(model.getOrigem()) || TSUtil.isEmpty(model.getOrigem().getId())) {

			retorno.append(Constantes.OBJETO_OBRIGATORIO_ORIGEM + "\n");

		}

		if (TSUtil.isEmpty(model.getEmpresa()) || (TSUtil.isEmpty(new EmpresaDAO().obter(model.getEmpresa())))) {

			retorno.append(Constantes.OBJETO_EMPRESA_OBRIGATORIO + "\n");

		} else {

			model.setEmpresa(new EmpresaDAO().obter(model.getEmpresa()));

		}

		if (TSUtil.isEmpty(retorno.toString())) {

			retorno.append(new DocumentoValidationAB().validarFilial(model));

		}

		NotaFiscalSaida nota = null;
		if (TSUtil.isEmpty(retorno.toString())) {

			nota = new NotaFiscalSaidaDAO().obterIdExterno(model);

			if (TSUtil.isEmpty(nota)) {

				retorno.append("Nota não existe ou está cancelada." + "\n");

			}

		}

		if (!TSUtil.isEmpty(retorno.toString())) {

			model.setMensagemErro(retorno.toString());

		}

		return !TSUtil.isEmpty(nota) ? nota : model;

	}

}
