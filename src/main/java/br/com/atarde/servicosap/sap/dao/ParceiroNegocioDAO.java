/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.atarde.servicosap.sap.dao;

import br.com.atarde.servicosap.sap.model.ParceiroNegocio;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

/**
 *
 * @author mroland
 */
public class ParceiroNegocioDAO {

    public ParceiroNegocioDAO() {
    }

    public ParceiroNegocio obterPorIdentificador(ParceiroNegocio model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT CRD7.CARDCODE, OCRD.UPDATEDATE, OCRD.SHIPTODEF, OCRD.BILLTODEF ");

        sql.append(" FROM DBO.CRD7 WITH(NOLOCK) INNER JOIN");

        sql.append(" DBO.OCRD WITH(NOLOCK) ON (CRD7.CARDCODE = OCRD.CARDCODE)");

        sql.append(" WHERE (CRD7.ADDRESS = '' OR CRD7.ADDRESS IS NULL)");

        sql.append(" AND OCRD.CARDTYPE = 'C'");

        switch (model.getIdentificadorFiscal().getTipoIdentificador().intValue()){

            case 0: //CNPJ

              sql.append(" AND CRD7.TAXID0= ?");

              break;

            case 1: //CPF

              sql.append(" AND CRD7.TAXID4= ?");

              break;

            case 2: //OUTROS

              sql.append(" AND CRD7.TAXID5= ?");

              break;

        }

        broker.setSQL(sql.toString());

        broker.set(model.getIdentificadorFiscal().getIdentificador());
        
        return (ParceiroNegocio) broker.getObjectBean(ParceiroNegocio.class, "id", "dataAtualizacao", "enderecoEntregaDefault", "enderecoCobrancaDefault");

    }

	public ParceiroNegocio obter(ParceiroNegocio model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		broker.setSQL("SELECT OCRD.CARDCODE,OCRD.SHIPTODEF AS ENDERECO_ENTREGA, OCRD.BILLTODEF AS ENDERECO_COBRANCA, CASE WHEN (OCRD.[VALIDFOR]='Y' OR (OCRD.[VALIDFOR]='N' AND OCRD.[FROZENFOR]='N')) THEN 1 ELSE 0 END AS FLAG_ATIVO FROM OCRD WITH(NOLOCK) WHERE OCRD.CARDCODE = ?", model.getId());
		
		return (ParceiroNegocio) broker.getObjectBean(ParceiroNegocio.class, "id", "enderecoEntregaDefault", "enderecoCobrancaDefault", "flagAtivo");
	}

}
