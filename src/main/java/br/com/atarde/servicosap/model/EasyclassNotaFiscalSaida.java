package br.com.atarde.servicosap.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import br.com.atarde.servicosap.sap.model.Empresa;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.atarde.servicosap.sap.model.ParceiroNegocio;
import br.com.atarde.servicosap.sap.model.Status;

@SuppressWarnings("serial")
@XmlRootElement
public class EasyclassNotaFiscalSaida extends NotaFiscalSaidaAB implements Serializable {

	private ParceiroNegocio anunciante;
    private BigDecimal uComissaoAgencia;
    private String uDiasPublicacao;
    private Date uDataPublicacaoFinal;
    private Date uDataPublicacaoInicial;    
    private String uTituloPublicacao;
    private String uAutorizacaoPublicidade;
    private String uNumeroPI;
    private String uTipoTransacao;
    private List<EasyclassNotaFiscalSaidaLinha> linhas;
    private String uPeriodo;
    private String uFormato;
    private BigDecimal uPageViews;
    private String uEntregaVendedor;
    private String uProduto;
    private String uCampanha;
    private String uPostoId;
    

    public EasyclassNotaFiscalSaida() {
    }
    
    public EasyclassNotaFiscalSaida(Status status) {
        
        this.setStatus(status);
        
    }

    public EasyclassNotaFiscalSaida(Empresa empresa) {
		this.setEmpresa(empresa);
	}

	public EasyclassNotaFiscalSaida(String atributo, Long valor) {

		if("interfaceId".equals(atributo)){
			
			this.setInterfaceId(valor);
			
		}
	}

	public BigDecimal getUComissaoAgencia() {
        return uComissaoAgencia;
    }

    public void setUComissaoAgencia(BigDecimal uComissaoAgencia) {
        this.uComissaoAgencia = uComissaoAgencia;
    }

    public Date getUDataPublicacaoFinal() {
        return uDataPublicacaoFinal;
    }

    public void setUDataPublicacaoFinal(Date uDataPublicacaoFinal) {
        this.uDataPublicacaoFinal = uDataPublicacaoFinal;
    }

    public String getUDiasPublicacao() {
        return uDiasPublicacao;
    }

    public void setUDiasPublicacao(String uDiasPublicacao) {
        this.uDiasPublicacao = uDiasPublicacao;
    }

    public String getUTituloPublicacao() {
        return uTituloPublicacao;
    }

    public void setUTituloPublicacao(String tituloPublicacao) {
        uTituloPublicacao = tituloPublicacao;
    }

    public String getUNumeroPI() {
        return uNumeroPI;
    }

    public void setUNumeroPI(String uNumeroPI) {
        this.uNumeroPI = uNumeroPI;
    }

    public String getUAutorizacaoPublicidade() {
        return uAutorizacaoPublicidade;
    }

    public void setUAutorizacaoPublicidade(String autorizacaoPublicidade) {
        uAutorizacaoPublicidade = autorizacaoPublicidade;
    }

    public String getUTipoTransacao() {
        return uTipoTransacao;
    }

    public void setUTipoTransacao(String uTipoTransacao) {
        this.uTipoTransacao = uTipoTransacao;
    }
    
    public String getUFormato() {
        return uFormato;
    }

    public void setUFormato(String uFormato) {
        this.uFormato = uFormato;
    }

    public BigDecimal getUPageViews() {
        return uPageViews;
    }

    public void setUPageViews(BigDecimal uPageViews) {
        this.uPageViews = uPageViews;
    }

    public String getUPeriodo() {
        return uPeriodo;
    }

    public void setUPeriodo(String uPeriodo) {
        this.uPeriodo = uPeriodo;
    }

	public List<EasyclassNotaFiscalSaidaLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<EasyclassNotaFiscalSaidaLinha> linhas) {
		this.linhas = linhas;
	}

	public String getUEntregaVendedor() {
		return uEntregaVendedor;
	}

	public void setUEntregaVendedor(String uEntregaVendedor) {
		this.uEntregaVendedor = uEntregaVendedor;
	}

	public String getUProduto() {
		return uProduto;
	}

	public void setUProduto(String uProduto) {
		this.uProduto = uProduto;
	}

	public String getUCampanha() {
		return uCampanha;
	}

	public void setUCampanha(String uCampanha) {
		this.uCampanha = uCampanha;
	}

	public String getUPostoId() {
		return uPostoId;
	}

	public void setUPostoId(String uPostoId) {
		this.uPostoId = uPostoId;
	}

	public Date getUDataPublicacaoInicial() {
		return uDataPublicacaoInicial;
	}

	public void setUDataPublicacaoInicial(Date uDataPublicacaoInicial) {
		this.uDataPublicacaoInicial = uDataPublicacaoInicial;
	}

	public ParceiroNegocio getAnunciante() {
		return anunciante;
	}

	public void setAnunciante(ParceiroNegocio anunciante) {
		this.anunciante = anunciante;
	}
  
}
