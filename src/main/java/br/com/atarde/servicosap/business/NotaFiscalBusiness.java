package br.com.atarde.servicosap.business;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import br.com.atarde.servicosap.dao.AssinaturaNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.dao.EasyclassNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.dao.EmpresaDAO;
import br.com.atarde.servicosap.dao.VendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaida;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.model.AssinaturaNotaFiscalSaidaParcela;
import br.com.atarde.servicosap.model.ClassificadosContratoNotaFiscalSaida;
import br.com.atarde.servicosap.model.EasyclassNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaLinha;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.atarde.servicosap.sap.dao.OrigemDAO;
import br.com.atarde.servicosap.sap.model.CFOP;
import br.com.atarde.servicosap.sap.model.CST;
import br.com.atarde.servicosap.sap.model.Classificacao;
import br.com.atarde.servicosap.sap.model.CodigoImposto;
import br.com.atarde.servicosap.sap.model.CondicaoPagamento;
import br.com.atarde.servicosap.sap.model.ContaContabil;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.Estado;
import br.com.atarde.servicosap.sap.model.GrupoComissao;
import br.com.atarde.servicosap.sap.model.IdentificadorFiscal;
import br.com.atarde.servicosap.sap.model.Item;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosap.sap.model.Origem;
import br.com.atarde.servicosap.sap.model.Pais;
import br.com.atarde.servicosap.sap.model.ParceiroNegocio;
import br.com.atarde.servicosap.sap.model.ParceiroNegocioEndereco;
import br.com.atarde.servicosap.sap.model.ParcelaAB;
import br.com.atarde.servicosap.sap.model.Sequencia;
import br.com.atarde.servicosap.sap.model.Status;
import br.com.atarde.servicosap.sap.model.Utilizacao;
import br.com.atarde.servicosap.sap.model.Vendedor;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.atarde.servicosap.validation.AssinaturaNotaFiscalSaidaValidation;
import br.com.atarde.servicosap.validation.ClassificadosContratoNotaFiscalSaidaValidation;
import br.com.atarde.servicosap.validation.EasyclassNotaFiscalSaidaValidation;
import br.com.atarde.servicosap.validation.VendaAvulsaNotaFiscalSaidaValidation;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;

public class NotaFiscalBusiness extends MainBusiness<NotaFiscalSaidaAB> {

	@Override
	public NotaFiscalSaidaAB inserir(NotaFiscalSaidaAB model) {

		switch (model.getOrigem().getId().intValue()) {

		case 1:// Easyclass

			if (model instanceof EasyclassNotaFiscalSaida) {

				return new EasyclassNotaFiscalSaidaBusiness().inserir((EasyclassNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia VendaAvulsaNotaFiscalSaida.");

			}

			break;

		case 2: // VendaAvulsa

			if (model instanceof VendaAvulsaNotaFiscalSaida) {

				return new VendaAvulsaNotaFiscalSaidaBusiness().inserir((VendaAvulsaNotaFiscalSaida) model);

			} else {

				model.setMensagemErro("Objeto NotaFiscalSaidaAB nao é uma instancia VendaAvulsaNotaFiscalSaida.");

			}

			break;

		case 3: // Assinatura

			if (model instanceof AssinaturaNotaFiscalSaida) {

				return new AssinaturaNotaFiscalSaidaBusiness().inserir((AssinaturaNotaFiscalSaida) model);

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

		// this.gerarXml();

		//this.inserirAssinatura();

		//this.inserirVendaAvulsa();

		if (!TSUtil.isEmpty(model) && !TSUtil.isEmpty(model.getOrigem()) && !TSUtil.isEmpty(Utilitarios.tratarLong(model.getOrigem().getId()))) {

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

	public void inserirVendaAvulsa() {

		VendaAvulsaNotaFiscalSaida nota = new VendaAvulsaNotaFiscalSaida();

		nota.setEmpresa(new Empresa(1L)); // COLOCAR CÓDIGO DA EMPRESA

		nota.setOrigem(new Origem(2L)); // CAMPO FIXO

		nota.setDataLancamento(new Date()); // COLOCAR A DATA DO DIA DA EXPORTAÇÃO

		nota.setDataDocumento(new Date()); // COLOCAR A DATA DO DIA DA EXPORTAÇÃO

		nota.setDataCriacao(new Date()); // COLOCAR A DATA DO DIA DA EXPORTAÇÃO

		nota.setIdExterno("VDA.654206"); // COLOCAR CÓDIGO DE INTEGRAÇÃO DO VENDA AVULSA)

		nota.setCliente(new ParceiroNegocio());

		nota.getCliente().setTipo("C"); // CAMPO FIXO

		nota.getCliente().setIdentificadorFiscal(new IdentificadorFiscal());

		nota.getCliente().getIdentificadorFiscal().setTipoIdentificador(0); // COLOCAR 1(PF) ou 0(PJ));

		nota.getCliente().getIdentificadorFiscal().setIdentificador("07533074000132"); // COLOCAR CPF ou CNPJ);

		nota.getCliente().getIdentificadorFiscal().setInscricaoEstadual("ISENTO"); // COLOCAR INSCRIÇÃO ESTADUAL);

		nota.getCliente().setNome("GHIA ENGENHARIA LTDA"); // COLOCAR NOME DO CLIENTE );
		nota.getCliente().setNomeFantasia("GHIA ENGENHARIA LTDA"); // COLOCAR NOME FANTASIA (PJ) OU NOME(PF));

		nota.getCliente().setEmail("encalhe@grupoatarde.com.br"); // COLOCAR E-MAIL DO CLIENTE );
		nota.getCliente().setTelefoneCelular("(71) 33471212"); // COLOCAR NRO DO CELULAR DO CLIENTE );
		nota.getCliente().setTelefoneResidencial(null); // COLOCAR NRO TELEFONE RESIDENCIAL DO CLIENTE);

		nota.getCliente().setClassificacao(new Classificacao());

		nota.getCliente().getClassificacao().setId(105L); // CAMPO FIXO

		nota.getCliente().setEndereco(new ParceiroNegocioEndereco());

		nota.getCliente().getEndereco().setTipoLogradouro("RUA"); // COLOCAR TIPO DO LOGRADOURO DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setLogradouro("RUBENS GUELLI"); // COLOCAR LOGRADOURO DO ENDEREÇO DO CLIENTE );

		nota.getCliente().getEndereco().setNumero("134"); // COLOCAR NUMERO DA RESIDENCIA DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setBairro("ITAIGARA"); // COLOCAR BAIRRO DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setCep("41815135"); // COLOCAR CEP DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setCidade("SALVADOR"); // COLOCAR CIDADE DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setEstado(new Estado());

		nota.getCliente().getEndereco().getEstado().setId("BA"); // COLOCAR UF DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setPais(new Pais());

		nota.getCliente().getEndereco().getPais().setId("BR"); // COLOCAR PAIS DO ENDEREÇO DO CLIENTE );

		nota.setVendedor(new Vendedor());

		nota.getVendedor().setId(-1L); // CAMPO FIXO

		nota.setValor(new BigDecimal("5000.00")); // COLOCAR VALOR DA NOTA

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyyy");

		try {

			nota.setDataVencimento((Date) formatter.parse("22/10/2024")); // COLOCAR DATA DE VENCIMENTO );

		} catch (ParseException e) {
		}

		nota.setSequencia(new Sequencia());

		nota.getSequencia().setId(28L); // CAMPO FIXO

		nota.setObservacao("OBSERVACAO DO PONTO DE VENDA DO CLIENTE");

		nota.setObservacao("Imunidade Tributária conforme Art. 150, alínea D, inciso VI da Constituição Federal."); // TEXTO FIXO

		nota.setUEnderecoEntrega("R. RUBENS GUELLI 134 ITAIGARA Cep : 41815135 SALVADOR-BA"); // COLOCAR ENDEREÇO DE ENTREGA DO CLIENTE );

		nota.setUObservacao("PERIODO 13/10/2024 ATE 13/10/2024"); // OBSERVAÇÃO DA BANCA

		nota.setUBanca("2606.2664-GHIA ENGENHARIA LTDA"); // COLOCAR NOME DA BANCA);

		nota.setULote("14754"); // COLOCAR NUMERO DO LOTE DE REMESSA );

		nota.setUTipoBanca("BANCA"); // TIPO DO PONTO DE VENDA);

		nota.setUTipoFaturamento("DIARIO"); // COLOCAR TIPO DE FATURAMENTO DA BANCA);

		nota.setLinhas(new ArrayList<VendaAvulsaNotaFiscalSaidaLinha>());

		VendaAvulsaNotaFiscalSaidaLinha linha = new VendaAvulsaNotaFiscalSaidaLinha();

		linha.setUtilizacao(new Utilizacao());

		linha.getUtilizacao().setId(16L); // CAMPO FIXO

		linha.setItem(new Item());

		linha.getItem().setId("VA00007"); // COLOCAR CODIGO DO ITEM );

		linha.setQuantidade(1000D); // COLOCAR QUANTIDADE DA VENDA);

		linha.setValor(new BigDecimal("5000.00")); // COLOCAR VALOR DA VENDA);

		linha.setCfop(new CFOP());

		linha.getCfop().setCodigo("5101"); // COLOCAR CODIGO 5101 (BA) OU 6101 OUTRO ESTADO

		linha.setCstCOFINS(new CST());

		linha.getCstCOFINS().setCodigo("01"); // COLOCAR CODIGO COFINS);

		linha.setCstICMS(new CST());

		linha.getCstICMS().setCodigo("0.41"); // COLOCAR CODIGO ICMS );

		linha.setCstIPI(new CST());

		linha.getCstIPI().setCodigo("54"); // COLOCAR CODIGO IPI );

		linha.setCstPIS(new CST());

		linha.getCstPIS().setCodigo("01"); // COLOCAR CODIGO PIS );

		linha.setContaContabil(new ContaContabil());

		linha.getContaContabil().setId("3.01.01.01.0101"); // COLOCAR CONTA CONTABIL );

		linha.setCodigoImposto(new CodigoImposto());

		linha.getCodigoImposto().setId("5101-006"); // COLOCAR CODIGO DO IMPOSTO );

		linha.setFlagImposto(false); // CAMPO FIXO

		nota.getLinhas().add(linha);

		nota.setRomaneios(new ArrayList<VendaAvulsaNotaFiscalSaidaRomaneio>());

		VendaAvulsaNotaFiscalSaidaRomaneio romaneio = new VendaAvulsaNotaFiscalSaidaRomaneio();

		romaneio.setIdExterno("VDA.654210"); // COLOCAR CÓDIGO DE INTEGRAÇÃO DO VENDA AVULSA );

		romaneio.setRoteiro("ROTEIRO DISTRIBUIDOR"); // COLOCAR ROTEIRO DA DISTRIBUIÇÃO

		romaneio.setDescricao("EXEMPLARES JORNAL - DOM 1A"); // COLOCAR DESCRICAO DO ITEM );

		try {

			romaneio.setData((Date) formatter.parse("13/10/2024")); // COLOCAR DATA DA VENDA);

		} catch (ParseException e) {
		}

		romaneio.setReparte(1000); // QUANTIDADE DE REPARTE);

		romaneio.setEncalhe(0); // COLOCAR QUANTIDADE DE ENCALHE);

		romaneio.setVenda(1000); // COLOCAR QUANTIDADE DA VENDA);

		romaneio.setPreco(new BigDecimal("5.00")); // COLOCAR PRECO DA VENDA);

		romaneio.setValor(new BigDecimal("5000.00")); // COLOCAR VALOR DA VENDA);

		romaneio.setRdj("000000"); // CAMPO FIXO

		romaneio.setRegiao("C"); // COLOCAR REGIAO DA BANCA );

		romaneio.setCidade("SALVADOR"); // COLOCAR CIDADE DA BANCA );

		nota.getRomaneios().add(romaneio);

		try {

			// Cria o contexto JAXB para a classe Pessoa
			JAXBContext context = JAXBContext.newInstance(AssinaturaNotaFiscalSaida.class);

			// Cria o Marshaller, que será responsável por converter a classe para XML
			Marshaller marshaller = context.createMarshaller();

			// Define que o XML será formatado com quebras de linha e indentação
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			System.out.println("Começo - XML de venda avulsa");
			// Gera e imprime o XML no console (sysout)
			marshaller.marshal(nota, System.out);
			System.out.println("Termino - XML de venda avulsa");

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	private void inserirAssinatura() {

		AssinaturaNotaFiscalSaida nota = new AssinaturaNotaFiscalSaida();

		nota.setEmpresa(new Empresa(1L)); // COLOCAR CÓDIGO DA EMPRESA

		nota.setOrigem(new Origem(3L)); // CAMPO FIXO

		nota.setDataLancamento(new Date()); // COLOCAR A DATA DO DIA DA EXPORTAÇÃO

		nota.setDataDocumento(new Date()); // COLOCAR A DATA DO DIA DA EXPORTAÇÃO

		nota.setDataCriacao(new Date()); // COLOCAR A DATA DO DIA DA EXPORTAÇÃO

		nota.setIdExterno("ASS24657"); // COLOCAR CÓDIGO DE INTEGRAÇÃO DO ASSINATURA

		nota.setCliente(new ParceiroNegocio());

		nota.setVendedor(new Vendedor());

		nota.getCliente().setTipo("C"); // CAMPO FIXO

		nota.getCliente().setIdentificadorFiscal(new IdentificadorFiscal());

		nota.getCliente().getIdentificadorFiscal().setTipoIdentificador(0); // COLOCAR 1(PF) ou 0(PJ));

		nota.getCliente().getIdentificadorFiscal().setIdentificador("07637870000115"); // COLOCAR CPF ou CNPJ);

		nota.getCliente().getIdentificadorFiscal().setInscricaoEstadual("ISENTO"); // COLOCAR INSCRIÇÃO ESTADUAL;

		nota.getCliente().setNome("AIG COMUNICACAO LDTA"); // COLOCAR NOME DO CLIENTE
		nota.getCliente().setNomeFantasia("AIG COMUNICACAO LDTA"); // COLOCAR NOME FANTASIA (PJ) OU NOME(PF)
		nota.getCliente().setEmail("olimpia@aegplus.com.br"); // COLOCAR E-MAIL DO CLIENTE
		nota.getCliente().setFax(null); // COLOCAR NRO DO FAX DO CLIENTE
		nota.getCliente().setTelefoneCelular("71991456079"); // COLOCAR NRO DO CELULAR DO CLIENTE
		nota.getCliente().setTelefoneResidencial(null); // COLOCAR NRO TELEFONE RESIDENCIAL DO CLIENTE);

		nota.getCliente().setClassificacao(new Classificacao());

		nota.getCliente().getClassificacao().setId(105L); // CAMPO FIXO

		nota.getCliente().setEndereco(new ParceiroNegocioEndereco());

		nota.getCliente().getEndereco().setTipoLogradouro("RUA"); // COLOCAR TIPO DO LOGRADOURO DO ENDEREÇO DO CLIENTE

		nota.getCliente().getEndereco().setLogradouro("ALCEU AMOROSO LIMA"); // COLOCAR LOGRADOURO DO ENDEREÇO DO CLIENTE );

		nota.getCliente().getEndereco().setNumero("470"); // COLOCAR NUMERO DA RESIDENCIA DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setBairro("CAMINHO DAS ARVORES"); // COLOCAR BAIRRO DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setCep("41820770"); // COLOCAR CEP DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setCidade("SALVADOR"); // COLOCAR CIDADE DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setEstado(new Estado());

		nota.getCliente().getEndereco().getEstado().setId("BA"); // COLOCAR UF DO ENDEREÇO DO CLIENTE);

		nota.getCliente().getEndereco().setPais(new Pais());

		nota.getCliente().getEndereco().getPais().setId("BR"); // COLOCAR PAIS DO ENDEREÇO DO CLIENTE );

		nota.setVendedor(new Vendedor());

		nota.getVendedor().setTipoIdentificador(0); // CAMPO FIXO

		nota.getVendedor().setIdentificador("11111111111111"); // CAMPO FIXO

		nota.getVendedor().setNome("DIRETO"); // CAMPO FIXO

		nota.getVendedor().setGrupoComissao(new GrupoComissao());

		nota.getVendedor().getGrupoComissao().setId(1L); // CAMPO FIXO

		nota.setValor(new BigDecimal("132.00")); // VALOR DA NOTA FISCAL );

		nota.setSequencia(new Sequencia());

		nota.getSequencia().setId(28L); // CAMPO FIXO

		nota.setObservacao("Imunidade Tributária conforme Art. 150, alínea D, inciso VI da Constituição Federal."); // TEXTO FIXO

		nota.setUTermo("NF-e emitida de acordo com os termos do Ajuste SINIEF 1/12."); // TEXTO FIXO

		nota.setUEnderecoEntrega("RUA ALCEU AMOROSO LIMA 470"); // COLOCAR ENDEREÇO DE ENTREGA DO CLIENTE );

		nota.setUObservacao("Não incidência do ICMS, conforme Art. 6, Inciso I do RICMS/BA, aprovado pelo Dercreto 6.284/1997. Imunidade Tributária conforme Art. 150, alínea D, inciso VI da Constituição Federal."); // COLOCAR OBSERVACAO DA NOTA FISCAL);

		nota.setLinhas(new ArrayList<AssinaturaNotaFiscalSaidaLinha>());

		AssinaturaNotaFiscalSaidaLinha linha = new AssinaturaNotaFiscalSaidaLinha();

		linha.setUtilizacao(new Utilizacao());

		linha.getUtilizacao().setId(16L); // CAMPO FIXO

		linha.setItem(new Item());

		linha.getItem().setId("AS00001"); // COLOCAR CODIGO DO ITEM );

		linha.setQuantidade(1D); // COLOCAR QUANTIDADE DA VENDA );

		linha.setValor(new BigDecimal("132.00")); // COLOCAR VALOR DA VENDA );

		linha.setContaContabil(new ContaContabil());

		linha.getContaContabil().setId("3.01.01.01.0201"); // COLOCAR CONTA CONTABIL DO ITEM );

		linha.setCodigoImposto(new CodigoImposto());

		linha.getCodigoImposto().setId("5101-006"); // COLOCAR CODIGO DO IMPOSTO

		nota.getLinhas().add(linha);

		/* nova implementacao p enviar parcelas */

		nota.setCondicaoPagamento(new CondicaoPagamento());

		nota.getCondicaoPagamento().setId(-1L); // COLOCAR CONDIÇÃO DE PAGAMENTO );

		nota.setParcelas(new ArrayList<ParcelaAB>());

		ParcelaAB p = new AssinaturaNotaFiscalSaidaParcela();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyyy");

		try {

			p.setDataVencimento((Date) formatter.parse("01/10/2024")); // COLOCAR DATA DE VENCIMENTO );

		} catch (ParseException e) {
		}

		p.setValor(new BigDecimal("132.00")); // COLOCAR VALOR DA PARCELA );

		nota.getParcelas().add(p);
		
		try {

			// Cria o contexto JAXB para a classe Pessoa
			JAXBContext context = JAXBContext.newInstance(AssinaturaNotaFiscalSaida.class);

			// Cria o Marshaller, que será responsável por converter a classe para XML
			Marshaller marshaller = context.createMarshaller();

			// Define que o XML será formatado com quebras de linha e indentação
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			System.out.println("Começo - XML de assinatura");
			// Gera e imprime o XML no console (sysout)
			marshaller.marshal(nota, System.out);
			System.out.println("Termino - XML de assinatura");

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	private void gerarXml() {
		AssinaturaNotaFiscalSaida nota = new AssinaturaNotaFiscalSaida();

		nota.setEmpresa(new Empresa(1L));

		nota.setOrigem(new Origem(2L));

		nota.setDataLancamento(new Date());

		nota.setDataDocumento(new Date());

		nota.setDataCriacao(new Date());

		nota.setIdExterno("123");

		nota.setCliente(new ParceiroNegocio());

		nota.setVendedor(new Vendedor());

		nota.getCliente().setTipo("C");

		nota.getCliente().setIdentificadorFiscal(new IdentificadorFiscal());

		nota.getCliente().getIdentificadorFiscal().setTipoIdentificador(0);

		nota.getCliente().getIdentificadorFiscal().setIdentificador("15111297000130");

		nota.getCliente().getIdentificadorFiscal().setInscricaoEstadual("12345");

		nota.getCliente().setNome("JornalAtarde");
		nota.getCliente().setNomeFantasia("JornalAtarde");
		nota.getCliente().setEmail("eil11@");
		nota.getCliente().setFax("");
		nota.getCliente().setTelefoneCelular("");
		nota.getCliente().setTelefoneResidencial("");

		nota.getCliente().setClassificacao(new Classificacao());

		nota.getCliente().getClassificacao().setId(105L);

		nota.getCliente().setEndereco(new ParceiroNegocioEndereco());

		nota.getCliente().getEndereco().setTipoLogradouro("rua");

		nota.getCliente().getEndereco().setLogradouro("av logr");

		nota.getCliente().getEndereco().setNumero("33");

		nota.getCliente().getEndereco().setBairro("Imbui");

		nota.getCliente().getEndereco().setCep("41720100");

		nota.getCliente().getEndereco().setCidade("Salvador");

		nota.getCliente().getEndereco().setEstado(new Estado());

		nota.getCliente().getEndereco().getEstado().setId("BA");

		nota.getCliente().getEndereco().setPais(new Pais());

		nota.getCliente().getEndereco().getPais().setId("BR");

		nota.setVendedor(new Vendedor());

		nota.getVendedor().setTipoIdentificador(0);

		nota.getVendedor().setIdentificador("11111111111111");

		nota.getVendedor().setNome("DIRETO");

		nota.getVendedor().setGrupoComissao(new GrupoComissao());

		nota.getVendedor().getGrupoComissao().setId(1L);

		nota.setValor(new BigDecimal(100));

		nota.setSequencia(new Sequencia());

		nota.getSequencia().setId(28L);

		nota.setObservacao("Imunidade Tributária conforme Art. 150, alínea D, inciso VI da Constituição Federal.");

		nota.setUTermo("NF-e emitida de acordo com os termos do Ajuste SINIEF 1/12.");

		nota.setUEnderecoEntrega("eee");

		nota.setUObservacao("ddd");

		nota.setLinhas(new ArrayList<AssinaturaNotaFiscalSaidaLinha>());

		AssinaturaNotaFiscalSaidaLinha linha = new AssinaturaNotaFiscalSaidaLinha();

		linha.setUtilizacao(new Utilizacao());

		linha.getUtilizacao().setId(16L);

		linha.setItem(new Item());

		linha.getItem().setId("PU0001");

		linha.setQuantidade(10D);

		linha.setValor(new BigDecimal(100));

		linha.setContaContabil(new ContaContabil());

		linha.getContaContabil().setId("2.2.2");

		linha.setCodigoImposto(new CodigoImposto());

		linha.getCodigoImposto().setId("5101-006");

		nota.getLinhas().add(linha);

		nota.setCondicaoPagamento(new CondicaoPagamento());

		nota.getCondicaoPagamento().setId(12L);

		nota.setParcelas(new ArrayList<ParcelaAB>());

		ParcelaAB p = new AssinaturaNotaFiscalSaidaParcela();

		p.setDataVencimento(new Date());

		p.setValor(new BigDecimal(100));

		nota.getParcelas().add(p);

		try {

			// Cria o contexto JAXB para a classe Pessoa
			JAXBContext context = JAXBContext.newInstance(AssinaturaNotaFiscalSaida.class);

			// Cria o Marshaller, que será responsável por converter a classe para XML
			Marshaller marshaller = context.createMarshaller();

			// Define que o XML será formatado com quebras de linha e indentação
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Gera e imprime o XML no console (sysout)
			marshaller.marshal(nota, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void inserirSAP(Empresa empresa) {

	}

	@Override
	public void alterarStatusInterface() throws TSApplicationException {
		// TODO Auto-generated method stub

	}

}
