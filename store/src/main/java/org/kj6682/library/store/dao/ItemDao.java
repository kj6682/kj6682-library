package org.kj6682.library.store.dao;

import java.util.List;

import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Status;

public interface ItemDao {

		public List<Item> listAll();
		public Item findById(Long id);
		public void updateStatus(Long id, Status status);
		
}
