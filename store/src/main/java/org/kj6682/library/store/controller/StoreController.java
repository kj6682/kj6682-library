package org.kj6682.library.store.controller;
import java.util.List;

import javax.annotation.Resource;

import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Status;
import org.kj6682.library.store.dao.ItemDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

	
	private ItemDao itemDao;
	
	public ItemDao getItemDao() {
		return itemDao;
	}
	
	@Resource(name="itemDao")
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	    
    @RequestMapping("/book")
    public Item book(@RequestParam(value="id") long id) {
    	itemDao.updateStatus(id, Status.RESERVED);
    	return itemDao.findById(id);
    }
    
    @RequestMapping("/extend")
    public Item extend(@RequestParam(value="id") long id) {
    	itemDao.updateStatus(id, Status.LENT);
    	return itemDao.findById(id);    
    }
    
    @RequestMapping("/grant")
    public Item grant(@RequestParam(value="id") long id) {
     	itemDao.updateStatus(id, Status.LENT);
    	return itemDao.findById(id);    
    }
    
    @RequestMapping("/return")
    public Item returnItem(@RequestParam(value="id") long id) {
    	itemDao.updateStatus(id, Status.AVAILABLE);
    	return itemDao.findById(id);    
    }
    
    @RequestMapping("/listAll")
    public List<Item> listAll() {
    	
        return itemDao.listAll();
    }
    
}//:)