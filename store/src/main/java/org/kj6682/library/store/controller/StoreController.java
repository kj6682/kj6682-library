package org.kj6682.library.store.controller;
import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Lending;
import org.kj6682.library.store.dao.ItemDao;
import org.kj6682.library.store.dao.LendingDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

	
	private ItemDao itemDao;
	private LendingDao lendingDao;
	
	public ItemDao getItemDao() {
		return itemDao;
	}
	
	@Resource(name="itemDao")
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public LendingDao getLendingDao(){
		return lendingDao;
	}
	    
	@Resource(name="lendingDao")
	public void setLendingDao(LendingDao lendingDao) {
		this.lendingDao = lendingDao;
	}

    @RequestMapping("/book")
    public Item book(@RequestParam(value="id") long id) {
    	itemDao.updateStatus(id, Item.Status.RESERVED);
    	return itemDao.findById(id);
    }
    
    @RequestMapping("/extend")
    public Item extend(@RequestParam(value="id") long id) {
    	itemDao.updateStatus(id, Item.Status.LENT);
    	return itemDao.findById(id);    
    }
    
    @RequestMapping("/grant")
    public Lending grant(@RequestParam(value="item") String item, @RequestParam(value="user") String user, @RequestParam(value="date") Date from) {
    	
    	Date to = new Date(new DateTime(from).plusDays(30).getMillis());
    	
    	long id = lendingDao.createLending(Long.valueOf(item), Long.valueOf(user), from, to, Lending.Status.ACTIVE);
    	itemDao.updateStatus(Long.valueOf(item), Item.Status.LENT);
    	
    	return new Lending(Long.valueOf(id), Long.valueOf(item), Long.valueOf(user), from, to, Lending.Status.ACTIVE);
    
    }
    
    @RequestMapping("/return")
    public Item returnItem(@RequestParam(value="id") long id) {
    	itemDao.updateStatus(id, Item.Status.AVAILABLE);
    	return itemDao.findById(id);    
    }
    
    @RequestMapping("/listAll")
    public List<Item> listAll() {
    	
        return itemDao.listAll();
    }
    
}//:)