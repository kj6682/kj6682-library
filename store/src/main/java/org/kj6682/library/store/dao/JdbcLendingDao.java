package org.kj6682.library.store.dao;

import java.sql.Date;

import org.kj6682.library.store.bean.Lending;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcLendingDao extends JdbcDaoSupport implements LendingDao{

	@Override
	public long createLending(long item, long user, Date from, Date to,
			Lending.Status status) {
		
	    String sql = "INSERT INTO LENDINGS (item, user, startdate, enddate, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        long id = getJdbcTemplate().update(sql, item, user, from, to, status.toString());
    	
        return id;
	}

	
}
