package org.kj6682.library.store.service;

import java.sql.Date;

import org.kj6682.library.store.bean.Item;

public interface ReservationService {

	/**
	 * The user reserves an item for a future lending
	 * The reservation should be valid for a short time period
	 * 
	 * The reservation is done directly on the item
	 * 
	 * @param id
	 * @return
	 */
	Item makeReservation(Long id, String user, Date startDate);

	/** 
	 * The reservation can be canceled at any time
	 * 
	 * @param id
	 * @param user
	 * @param startDate
	 * @return
	 */
	Item cancelReservation(Long id, String user, Date startDate);

}