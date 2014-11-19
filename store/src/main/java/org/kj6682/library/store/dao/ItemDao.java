package org.kj6682.library.store.dao;

import java.util.List;

import org.kj6682.library.store.bean.Item;

public interface ItemDao {

	    public Item create(long catalogId);
		public Item findById(long id);
		public Item update(long id, Item.Status status);
		public void delete(long id);
		public List<Item> listAll();
		public List<Item> listByCatalog(long id);
		
}
