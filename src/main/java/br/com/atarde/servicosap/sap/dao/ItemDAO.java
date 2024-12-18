package br.com.atarde.servicosap.sap.dao;

import java.util.List;

import br.com.atarde.servicosap.sap.model.Item;
import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;

public class ItemDAO {

	public Item obter(Item model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("itemdao.obter", model.getId());

        return (Item) broker.getObjectBean(Item.class, "id");
        
	}

	@SuppressWarnings("unchecked")
	public List<Item> pesquisar(Item model) {

        TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf(model.getEmpresa().getJndi());

        broker.setPropertySQL("itemdao.pesquisar", model.getEstoque().getId());

        return broker.getCollectionBean(Item.class, "catalogo","id", "descricao", "preco", "estoqueItem.quantidadeDisponivel");
	}

}
