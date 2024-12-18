package br.com.atarde.servicosap.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.atarde.servicosap.dao.HistoricoVendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.dao.VendaAvulsaNotaFiscalSaidaDAO;
import br.com.atarde.servicosap.dao.VendaAvulsaNotaFiscalSaidaLinhaDAO;
import br.com.atarde.servicosap.dao.VendaAvulsaNotaFiscalSaidaRomaneioDAO;
import br.com.atarde.servicosap.model.HistoricoVendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaida;
import br.com.atarde.servicosap.model.VendaAvulsaNotaFiscalSaidaRomaneio;
import br.com.atarde.servicosap.sap.diapi.VendaAvulsaNotaFiscalSaidaSapDiApiDAO;
import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosap.sap.model.Status;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSStringUtil;
import br.com.topsys.util.TSUtil;

public class VendaAvulsaNotaFiscalSaidaBusiness {

	public void inserirSAP(Empresa model) {

		List<VendaAvulsaNotaFiscalSaida> lista = new ArrayList<VendaAvulsaNotaFiscalSaida>();

		for (VendaAvulsaNotaFiscalSaida item : new VendaAvulsaNotaFiscalSaidaDAO().pesquisarInterface(new VendaAvulsaNotaFiscalSaida(model))) {

			try {

				item.setEmpresa(model);

				item.setLinhas(new VendaAvulsaNotaFiscalSaidaLinhaDAO().pesquisarInterface(item));

				item.setRomaneios(new VendaAvulsaNotaFiscalSaidaRomaneioDAO().pesquisarInterface(item));

				item.setStatus(new Status(2L));

				item.setMensagemErro(null);
				
				new VendaAvulsaNotaFiscalSaidaDAO().alterarInterface(item);

				lista.add(item);

			} catch (TSApplicationException e) {

				item.setStatus(new Status(3L));

				item.setDataImportacao(new Timestamp(System.currentTimeMillis()));

				if (!TSUtil.isEmpty(e.getMessage())) {

					item.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

				} else {

					item.setMensagemErro("erro Banco");

				}

				try {

					new HistoricoVendaAvulsaNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(item));

					new VendaAvulsaNotaFiscalSaidaDAO().alterarInterface(item);

				} catch (TSApplicationException e1) {

					e1.printStackTrace();

				}

			}

		}

		for (VendaAvulsaNotaFiscalSaida item : lista) {

			this.inserir(item);

		}

	}

	public NotaFiscalSaidaAB inserir(VendaAvulsaNotaFiscalSaida model) {

		try {

			model.getCliente().setEmpresa(model.getEmpresa());

			new ParceiroNegocioBusiness().validarClienteComEndereco(model.getCliente());

			model.getVendedor().setEmpresa(model.getEmpresa());

			new VendedorBusiness().validar(model.getVendedor());

			new VendaAvulsaNotaFiscalSaidaSapDiApiDAO().inserir((VendaAvulsaNotaFiscalSaida) model);

			model.setStatus(new Status(1L));

			model.setMensagemErro(null);

			new HistoricoVendaAvulsaNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

			this.inserirRomaneiosMSSQL(model);

			new VendaAvulsaNotaFiscalSaidaDAO().excluirInterface(model);

		} catch (TSApplicationException e) {

			model.setStatus(new Status(3L));

			model.setDataImportacao(new Timestamp(System.currentTimeMillis()));

			if (!TSUtil.isEmpty(e.getMessage())) {

				model.setMensagemErro(TSStringUtil.rightPad(e.getMessage(), 500, " ").substring(0, 500).trim());

			} else {

				model.setMensagemErro("erro Banco");

			}

			try {

				new HistoricoVendaAvulsaNotaFiscalSaidaDAO().inserirInterface(this.carregaHistorico(model));

				new VendaAvulsaNotaFiscalSaidaDAO().alterarInterface(model);

			} catch (TSApplicationException e1) {

				e1.printStackTrace();

			}

		}

		return model;

	}

	private void inserirRomaneiosMSSQL(VendaAvulsaNotaFiscalSaida model) throws TSApplicationException {
		
		if(!TSUtil.isEmpty(model.getRomaneios()) && model.getRomaneios().size()>0 ){
			
			for (VendaAvulsaNotaFiscalSaidaRomaneio item : model.getRomaneios()) {

				item.setEmpresa(model.getEmpresa());
				
				item.setNota(new VendaAvulsaNotaFiscalSaida(model.getId()));

				new VendaAvulsaNotaFiscalSaidaRomaneioDAO().inserirInterfaceMSSQL(item);

			}
			
		}

	}

	private HistoricoVendaAvulsaNotaFiscalSaida carregaHistorico(VendaAvulsaNotaFiscalSaida model) {

		HistoricoVendaAvulsaNotaFiscalSaida nota = new HistoricoVendaAvulsaNotaFiscalSaida();

		nota.setAtualizadoPor(model.getAtualizadoPor());

		nota.setCliente(model.getCliente());

		nota.setCondicaoPagamento(model.getCondicaoPagamento());

		nota.setCriadoPor(model.getCriadoPor());

		nota.setDataAtualizacao(model.getDataAtualizacao());

		nota.setDataCriacao(model.getDataCriacao());

		nota.setDataDocumento(model.getDataDocumento());

		nota.setDataExportacao(model.getDataExportacao());

		nota.setDataImportacao(model.getDataImportacao());

		nota.setDataLancamento(model.getDataLancamento());

		nota.setDataVencimento(model.getDataVencimento());

		nota.setEmpresa(model.getEmpresa());

		nota.setId(model.getId());

		nota.setIdExterno(model.getIdExterno());

		nota.setInterfaceId(model.getInterfaceId());

		nota.setLinhas(model.getLinhas());

		nota.setMensagemErro(model.getMensagemErro());

		nota.setObservacao(model.getObservacao());

		nota.setOrigem(model.getOrigem());

		nota.setParcela(model.getParcela());

		nota.setParcelas(model.getParcelas());

		nota.setPedidoVenda(model.getPedidoVenda());

		nota.setPercentualDesconto(model.getPercentualDesconto());

		nota.setRomaneios(model.getRomaneios());

		nota.setSequencia(model.getSequencia());

		nota.setSerial(model.getSerial());

		nota.setStatus(model.getStatus());

		nota.setUBanca(model.getUBanca());

		nota.setUEnderecoEntrega(model.getUEnderecoEntrega());

		nota.setULote(model.getULote());

		nota.setUObservacao(model.getUObservacao());

		nota.setURdj(model.getURdj());

		nota.setUTipoBanca(model.getUTipoBanca());

		nota.setUTipoFaturamento(model.getUTipoFaturamento());

		nota.setUValorBruto(model.getUValorBruto());

		nota.setValor(model.getValor());

		nota.setVendedor(model.getVendedor());

		return nota;

	}

	public void alterarStatusInterface() throws TSApplicationException {

		for (VendaAvulsaNotaFiscalSaida item : new VendaAvulsaNotaFiscalSaidaDAO().pesquisarPorAtrasadaInterface(new VendaAvulsaNotaFiscalSaida(new Status(2L)))) {

			item.setStatus(new Status(0L));

			item.setMensagemErro(null);

			new VendaAvulsaNotaFiscalSaidaDAO().alterarInterface(item);

		}


	}	
}
