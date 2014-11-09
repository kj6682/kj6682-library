package org.kj6682.library.store.controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.kj6682.library.store.bean.Item;
import org.kj6682.library.store.bean.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

	private DataSource dataSource;
	
	@Resource(name="dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
    
    @RequestMapping("/book")
    public Item book(@RequestParam(value="id", defaultValue="0") long id) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    	jdbcTemplate.update("update items set status = 'RESERVED' where id = ? ", new Object[] { id });
    	
    	Item item = jdbcTemplate.queryForObject("select * from items where id = ?", new Object[]{id}, 
                new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Item(rs.getLong("id"), rs.getString("catalogId"),
                        rs.getString("status"));
            }
        });
        return item;
    }
    
    @RequestMapping("/extend")
    public Item extend(@RequestParam(value="id", defaultValue="0") long id) {
        return new Item(id, Status.LENT);
    }
    
    @RequestMapping("/grant")
    public Item grant(@RequestParam(value="id", defaultValue="0") long id) {
        return new Item(id, Status.LENT);
    }
    
    @RequestMapping("/return")
    public Item returnItem(@RequestParam(value="id", defaultValue="0") long id) {
        return new Item(id, Status.AVAILABLE);
    }
    
    @RequestMapping("/listAll")
    public List<Item> listAll() {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    		
    	List<Item> results = jdbcTemplate.query(
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
}//:)