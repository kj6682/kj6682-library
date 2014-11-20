package org.kj6682.library.store.service;

import java.sql.Date;
import java.util.List;

import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Lending;

public interface LendingService {

	/**
	 * The administrator grants a lending to the user.
	 * The item gets out of the library and will be back in the period contracted
	 * 
	 * @param item
	 * @param user
	 * @param startDate
	 * @return
	 */
	public Lending grant(Long item, Long user, Date startDate);

	/**
	 * The user can extend its lending once
	 * 
	 * @param id
	 * @return
	 */
	public Lending extendLending(Long id);

	/**
	 * The item has been returned
	 * 
	 * @param id
	 * @return
	 */
	public Lending closeLending(Long id);


	/**
	 * List all the lendings granted to the user
	 * 
	 * @param user
	 * @return
	 */
	public List<Lending> listLendingsByUser(String user);

}
