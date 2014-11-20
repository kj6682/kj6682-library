package org.kj6682.library.store.service;

import java.util.List;

import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.dao.ItemDao;

public class ItemServiceImpl implements ItemService{

	
	ItemDao dao;
	
	public ItemDao getDao() {
		return dao;
	}

	public void setDao(ItemDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Item createItem(Long catalogId) {
		return dao.create(catalogId);
	}

	@Override
	public void deleteItem(Long id) {
		Item item = dao.findById(id);
		if(item == null) throw new RuntimeException("item.does.not.exist");
		dao.delete(id);
	}


	@Override
	public List<Item> listAllItems() {
		
		return dao.listAll();
	}

	@Override
	public List<Item> listItemsByCatalogId(Long catalogId) {
		
		return dao.listByCatalog(catalogId);
		
	}

}
