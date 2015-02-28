package org.kj6682.library.dao;

import static org.kj6682.library.bean.Item.Status.AVAILABLE;
import static org.kj6682.library.bean.Item.Status.valueOf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.kj6682.library.bean.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcItemDao extends JdbcDaoSupport implements ItemDao {

	@Override
	public Item create(long catalogId) {

		String sql = "INSERT INTO items (catalogId, status) VALUES(?,?)";

		long id = getJdbcTemplate().update(sql, catalogId, AVAILABLE);

		return new Item(id, catalogId, AVAILABLE);
	}

	@Override
	public List<Item> listAll() {

		String sql = "select * from items";

		List<Item> results = getJdbcTemplate().query(sql, new RowMapper<Item>() {
			@Override
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Item(rs.getLong("id"), rs.getLong("catalogId"), valueOf(rs.getString("status")));
			}
		});

		return results;
	}

	@Override
	public Item findById(long id) {

		String sql = "select * from items where id = ?";

		return getJdbcTemplate().queryForObject(sql, new Object[] { id }, new RowMapper<Item>() {
			@Override
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Item(rs.getLong("id"), rs.getLong("catalogId"), valueOf(rs.getString("status")));
			}
		});
	}

	@Override
	public Item update(long id, Item.Status status) {
		
		String sql = "update items set status = ? where id = ? ";
		
		getJdbcTemplate().update(sql, new Object[] { status.toString(), id });
		
		return findById(id);

	}

	@Override
	public void delete(long id) {

		String sql = "DELETE FROM items WHERE id = ? ";

		getJdbcTemplate().update(sql, new Object[] { id });

	}

	@Override
	public List<Item> listByCatalog(long id) {
		String sql = "select * from items where catalogId = ?";

		List<Item> results = getJdbcTemplate().query(sql, new Object[] { id }, new RowMapper<Item>() {
			@Override
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new Item(rs.getLong("id"), rs.getLong("catalogId"), valueOf(rs.getString("status")));
			}
		});
		
		return results;
	}

}
