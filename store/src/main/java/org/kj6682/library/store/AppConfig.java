package org.kj6682.library.store;


import javax.sql.DataSource;

import org.kj6682.library.store.dao.ItemDao;
import org.kj6682.library.store.dao.JdbcItemDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@ComponentScan(basePackages="org.kj6682.library")
public class AppConfig {

    @Bean(name="dataSource")
    public DataSource dataSource() {
    	SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUsername("sa");
        dataSource.setUrl("jdbc:h2:mem");
        dataSource.setPassword("");
        return dataSource;
    }
    
    @Bean(name="itemDao")
    public ItemDao itemDao(){
    	JdbcItemDao jdbcItemDao = new JdbcItemDao();
    	jdbcItemDao.setDataSource(dataSource());
    	return jdbcItemDao;
    }
    
}//:)

