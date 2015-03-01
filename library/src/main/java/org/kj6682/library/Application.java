package org.kj6682.library;
import javax.sql.DataSource;

import org.kj6682.library.dao.ItemDao;
import org.kj6682.library.dao.JdbcItemDao;
import org.kj6682.library.dao.JdbcLendingDao;
import org.kj6682.library.dao.LendingDao;
import org.kj6682.library.service.ContactService;
import org.kj6682.library.service.ContactServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mangofactory.swagger.plugin.EnableSwagger;

@SpringBootApplication
@EnableSwagger
public class Application {

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
    
    @Bean(name="lendingDao")
    public LendingDao lendingDao(){
    	JdbcLendingDao jdbcLendingDao = new JdbcLendingDao();
    	jdbcLendingDao.setDataSource(dataSource());
    	return jdbcLendingDao;
    }
    
    @Bean(name="contactService")
    public ContactService contactService(){
    	return new ContactServiceImpl();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}