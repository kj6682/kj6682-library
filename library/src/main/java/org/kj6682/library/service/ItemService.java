package org.kj6682.library.service;

import java.util.List;

import org.kj6682.library.bean.Item;

/**
 * This interface defines the actions that can be done on items only
 * 
 *
 */
public interface ItemService {

	public Item createItem(Long catalogId);

	public void deleteItem(Long id);

	/**
	 * Do we really need a dump method for every item?
	 * 
	 * @return
	 */
	public List<Item> listAllItems();

	
	/**
	 * List all items that share the same catalog id
	 * 
	 * @param catalogId
	 * @return
	 */
	public List<Item> listItemsByCatalogId(Long catalogId);

}
