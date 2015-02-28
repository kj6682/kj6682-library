package org.kj6682.library.dao;

import java.sql.Date;
import java.util.List;

import org.kj6682.library.bean.Item;
import org.kj6682.library.bean.Lending;

public interface LendingDao {

	public long create(long item, long user, Date from, Date to, Lending.Status status);
	public Lending findById(long id);
	public Lending findByItemAndUser(long item, long user);

	public Lending update(long id, long item, long user, Date from, Date to, Lending.Status status);
	public long delete(long id);
	
	public List<Lending> listAll();

}
