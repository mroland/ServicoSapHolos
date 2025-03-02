package br.com.atarde.servicosap.servlet;

import java.util.Calendar;

public class Main {

	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		
		c.setTimeInMillis(1729989171593L);
		
		System.out.println(c.getTime());
		
		String tel  = "(71) 99601-0033.";
		
		tel = tel.replace(".", "").replace("/", "").replace("-", "").replace("|", "").replace("\\", "").replace("(", "").replace(")", "").replaceAll(" ", "");
		
		if (tel.length() == 11) {
			
			tel= "(".concat(tel.substring(0, 2)).concat(")").concat(tel.substring(2, 7)).concat("-").concat(tel.substring(7, 11));
			
		}
		
		//(99) 99999-9999
		
		//tel = (tel.substring(0, 5).concat("-").concat(tel.substring(5, 8)));
		
		System.out.println(tel);
		
		/*

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
		
		Gson gson = new GsonBuilder()
	            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	            .create();

		System.out.println(gson.toJson(nota));
		
		*/

	}

}
