package org.kj6682.library.store.service;

import java.util.List;

import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;

import static org.kj6682.library.store.bean.Item.Status.*;

public class ItemServiceImpl implements ItemService{

	
	ItemDao dao;
	
	public ItemDao getDao() {
		return dao;
	}

	public void setDao(ItemDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Item create(Long catalogId) {
		return dao.create(catalogId);
	}

	@Override
	public void delete(Long id) {
		Item item = dao.findById(id);
		if(item == null) throw new RuntimeException("item.does.not.exist");
		dao.delete(id);
	}

	@Override
	public Item reserve(Long id) {
		Item item = dao.findById(id);
		if(item == null) throw new RuntimeException("item.does.not.exist");
		return dao.update(id, RESERVED);
	}

	@Override
	public Item release(Long id) {
		Item item = dao.findById(id);
		if(item == null) throw new RuntimeException("item.does.not.exist");
		return dao.update(id, AVAILABLE);
	}

	@Override
	public Item extend(Long id) {
		Item item = dao.findById(id);
		if(item == null) throw new RuntimeException("item.does.not.exist");
		return dao.update(id, LENT);
	}
	
	@Override
	public List<Item> listAll() {
		
		return dao.listAll();
	}

	@Override
	public List<Item> listByCatalog(Long id) {
		
		return dao.listByCatalog(id);
	}

}
