package org.kj6682.library.store.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Status;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcItemDao extends JdbcDaoSupport implements ItemDao{

	@Override
	public List<Item> listAll() {
    		
    	List<Item> results = getJdbcTemplate().query(
                "select * from items",
                new RowMapper<Item>() {
                    @Override
                    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Item(rs.getLong("id"), rs.getString("catalogId"),
                                rs.getString("status"));
                    }
                });
    	
        return results;
    }
	
	@Override
	public Item findById(Long id) {
    	return getJdbcTemplate().queryForObject("select * from items where id = ?", new Object[]{id}, 
                new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Item(rs.getLong("id"), rs.getString("catalogId"),
                        rs.getString("status"));
            }
        });
	}
	
	@Override
	public void updateStatus(Long id, Status status) {
		getJdbcTemplate().update("update items set status = ? where id = ? ", new Object[] { status.toString() , id });
    	

	}

	@Override
	protected void checkDaoConfig() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}
	

}
