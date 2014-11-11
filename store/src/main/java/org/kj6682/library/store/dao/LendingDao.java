package org.kj6682.library.store.dao;

import java.sql.Date;

import org.kj6682.library.store.bean.Lending;

public interface LendingDao {

	public long createLending(long item, long user, Date from, Date to, Lending.Status status);
	
}
