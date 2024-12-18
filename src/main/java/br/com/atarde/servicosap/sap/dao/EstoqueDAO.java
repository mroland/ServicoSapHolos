package br.com.atarde.servicosap.sap.dao;

import java.util.List;

import br.com.atarde.servicosap.sap.model.Estoque;
import br.com.atarde.servicosap.util.Utilitarios;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;

public class EstoqueDAO {
	
	@SuppressWarnings("unchecked")
	public List<Estoque> pesquisar(Estoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ESTOQUE.WHSCODE AS ID, ESTOQUE.WHSNAME AS NOME FROM OWHS ESTOQUE WITH(NOLOCK) WHERE EXISTS (SELECT 1 FROM OITM ITEM WITH(NOLOCK) INNER JOIN OITW LINHA WITH(NOLOCK) ON (LINHA.ITEMCODE = ITEM.ITEMCODE) WHERE ESTOQUE.WHSCODE = LINHA.WHSCODE AND LINHA.OnHand > 0 AND ITEM.U_CATALOGO IS NOT NULL ");
		
		if(!TSUtil.isEmpty(model.getItem()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getItem().getId()))){
			
			sql.append(" AND ITEM.ITEMCODE = ?");

		}else{
			
			sql.append(" )");
		}
		
		broker.setSQL(sql.toString());
		
		if(!TSUtil.isEmpty(model.getItem()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getItem().getId()))){
			
			broker.set(model.getItem().getId());
			
		}
			
		return  broker.getCollectionBean(Estoque.class, "id", "descricao");
		
	}
	
	public Estoque obter(Estoque model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ESTOQUE.WHSCODE AS ID, ESTOQUE.WHSNAME AS NOME FROM OWHS ESTOQUE WITH(NOLOCK) WHERE EXISTS (SELECT 1 FROM OITM ITEM WITH(NOLOCK) INNER JOIN OITW LINHA WITH(NOLOCK) ON (LINHA.ITEMCODE = ITEM.ITEMCODE) WHERE ESTOQUE.WHSCODE = LINHA.WHSCODE AND LINHA.OnHand > 0 ");
		
		if(!TSUtil.isEmpty(model.getItem()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getItem().getId()))){
			
			sql.append(" AND ITEM.ITEMCODE = ? )");

		}else{
			
			sql.append(" )");
		}
		
		broker.setSQL(sql.toString());
		
		if(!TSUtil.isEmpty(model.getItem()) && !TSUtil.isEmpty(Utilitarios.tratarString(model.getItem().getId()))){
			
			broker.set(model.getItem().getId());
			
		}
			
		return  (Estoque) broker.getObjectBean(Estoque.class, "id", "descricao");
		
	}


}
