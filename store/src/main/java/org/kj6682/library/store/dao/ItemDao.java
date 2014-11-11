package org.kj6682.library.store.dao;

import java.util.List;

import org.kj6682.library.store.bean.Item;

public interface ItemDao {

		public List<Item> listAll();
		public Item findById(Long id);
		public void updateStatus(Long id, Item.Status status);
		
}
