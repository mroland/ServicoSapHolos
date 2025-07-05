package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.NotaFiscalSaida;
import br.com.atarde.servicosap.sap.model.NotaFiscalSaidaAB;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class NotaFiscalSaidaDAO {

	public NotaFiscalSaida obterIdExterno(NotaFiscalSaidaAB model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

		broker.setSQL("SELECT OINV.\"DocEntry\", OINV.\"NumAtCard\", OINV.\"TaxDate\", OINV.\"Serial\", OINV.\"SeqCode\", (SELECT O.\"NfmName\" FROM " + model.getEmpresa().getDbInstancia() + ".ONFM O WHERE O.\"AbsEntry\" = OINV.\"Model\" ) FROM " + model.getEmpresa().getDbInstancia() + ".OINV WHERE OINV.\"NumAtCard\" = ? AND OINV.\"U_ATRD_Origem\"  = ? AND NOT EXISTS (SELECT 1 FROM " + model.getEmpresa().getDbInstancia() + ".OINV C, " + model.getEmpresa().getDbInstancia() + ".INV1 L WHERE C.\"DocEntry\" = L.\"DocEntry\" AND L.\"BaseEntry\" = OINV.\"DocEntry\" AND C.CANCELED = 'Y') AND OINV.CANCELED ='N' ", model.getIdExterno(), model.getOrigem().getId());

		return (NotaFiscalSaida) broker.getObjectBean(NotaFiscalSaida.class, "id", "idExterno", "dataDocumento", "serial", "sequencia.id", "sequencia.sequenciaModelo.descricao");

	}

}
