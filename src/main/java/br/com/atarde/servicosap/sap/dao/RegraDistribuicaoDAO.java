package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.RegraDistribuicao;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class RegraDistribuicaoDAO {

	public RegraDistribuicao obter(RegraDistribuicao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OOCR.\"OcrCode\", OOCR.\"OcrName\" FROM " + model.getEmpresa().getDbInstancia() + ".OOCR WHERE OOCR.\"DimCode\" = ? AND OOCR.\"OcrCode\" = ?", model.getDimensao().getId(), model.getId());

		return (RegraDistribuicao) broker.getObjectBean(RegraDistribuicao.class, "id", "descricao");

	}

}
