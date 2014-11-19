package org.kj6682.library.store;


import javax.sql.DataSource;

import org.kj6682.library.store.dao.ItemDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages="org.kj6682.library")
@Profile("dev")
public class DevConfig {

	
	
}//:)

