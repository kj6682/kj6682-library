package org.kj6682.library.store.service;

import java.util.List;

import org.kj6682.library.store.bean.Item;

public interface ItemService {

	public Item create(Long catalogId);
    public void delete(Long id);
    public Item reserve(Long id);
    public Item extend(Long id);
    public Item release(Long id);
    public List<Item> listAll();
    public List<Item> listByCatalog(Long id);
    
}
