package org.kj6682.library.store.service;

import static org.kj6682.library.store.bean.Item.Status.AVAILABLE;
import static org.kj6682.library.store.bean.Item.Status.LENT;
import static org.kj6682.library.store.bean.Item.Status.RESERVED;

import java.sql.Date;

import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.dao.ItemDao;

public class ReservationServiceImpl implements ReservationService {

	ItemDao dao;

	@Override
	public Item makeReservation(Long id, String user, Date startDate) {

		// TODO manage Reservation objects
		Item item = dao.findById(id);
		if (item == null)
			throw new RuntimeException("item.does.not.exist");
		return dao.update(id, RESERVED);
	}

	@Override
	public Item cancelReservation(Long id, String user, Date startDate) {

		Item item = dao.findById(id);
		if (item == null)
			throw new RuntimeException("item.does.not.exist");
		return dao.update(id, AVAILABLE);

	}

	public ItemDao getDao() {
		return dao;
	}

	public void setDao(ItemDao dao) {
		this.dao = dao;
	}

}// :)
