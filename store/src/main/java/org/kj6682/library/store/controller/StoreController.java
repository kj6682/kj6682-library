package org.kj6682.library.store.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Lending;
import org.kj6682.library.store.dao.ItemDao;
import org.kj6682.library.store.dao.LendingDao;
import org.kj6682.library.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

	@Autowired
	private ItemService itemService;

	private LendingDao lendingDao;

	public LendingDao getLendingDao() {
		return lendingDao;
	}

	@Resource(name = "lendingDao")
	public void setLendingDao(LendingDao lendingDao) {
		this.lendingDao = lendingDao;
	}

	@RequestMapping("/book")
	public StoreReply<Item> book(@RequestParam(value = "id") long id) {
		Item item;
		try {
			item = itemService.reserve(id);
		} catch (Exception e) {
			return new StoreReply<Item>("ERROR", "Impossible to book", null);
		}
		Set<Item> items = (new TreeSet<Item>());
		items.add(item);
		return new StoreReply<Item>("BOOKED", "", items);
	}

	@RequestMapping("/extend")
	public Item extend(@RequestParam(value = "id") long id) {
		return itemService.extend(id);
	}

	@RequestMapping("/grant")
	public Lending grant(@RequestParam(value = "item") String item, @RequestParam(value = "user") String user,
			@RequestParam(value = "date") Date from) {

		Date to = new Date(new DateTime(from).plusDays(30).getMillis());

		//
		return null;
	}

	@RequestMapping("/return")
	public Item returnItem(@RequestParam(value = "id") long id) {
		return null;
	}

	@RequestMapping("/listAll")
	public List<Item> listAll() {

		return null;
	}

}// :)