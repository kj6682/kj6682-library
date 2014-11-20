package org.kj6682.library.store.dao;

import java.sql.Date;
import java.util.List;

import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Lending;
import org.kj6682.library.store.bean.Lending.Status;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcLendingDao extends JdbcDaoSupport implements LendingDao{

	@Override
	public long create(long item, long user, Date from, Date to,
			Lending.Status status) {
		
	    String sql = "INSERT INTO LENDINGS (item, user, startdate, enddate, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        long id = getJdbcTemplate().update(sql, item, user, from, to, status.toString());
    	
        return id;
	}


	@Override
	public long delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Lending> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


	@Override
	public Lending findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Lending update(long id, long item, long user, Date from, Date to, Status status) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Lending findByItemAndUser(long item, long user) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
