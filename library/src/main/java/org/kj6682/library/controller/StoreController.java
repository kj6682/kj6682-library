package org.kj6682.library.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.kj6682.library.bean.Item;
import org.kj6682.library.bean.Lending;
import org.kj6682.library.dao.ItemDao;
import org.kj6682.library.dao.LendingDao;
import org.kj6682.library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;


@Api(value = "store", description = "store of a bloody application")
@RestController
public class StoreController {

	@Autowired
	private ReservationService itemService;

	private LendingDao lendingDao;

	public LendingDao getLendingDao() {
		return lendingDao;
	}

	@Resource(name = "lendingDao")
	public void setLendingDao(LendingDao lendingDao) {
		this.lendingDao = lendingDao;
	}

//	@RequestMapping("/book")
//	public StoreReply<Item> book(@RequestParam(value = "id") long id) {
//		Item item;
//		try {
//			item = itemService.makeReservation(id);
//		} catch (Exception e) {
//			return new StoreReply<Item>("ERROR", "Impossible to book", null);
//		}
//		Set<Item> items = (new TreeSet<Item>());
//		items.add(item);
//		return new StoreReply<Item>("BOOKED", "", items);
//	}
//
//	@RequestMapping("/extend")
//	public Item extend(@RequestParam(value = "id") long id) {
//		return itemService.extend(id);
//	}

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
        Item i = new Item(1, 1, Item.Status.AVAILABLE);
        List<Item> result = new ArrayList<Item>();
        result.add(i);
		return result;
	}

}// :)