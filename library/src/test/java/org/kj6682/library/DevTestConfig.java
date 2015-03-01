package org.kj6682.library;

import org.kj6682.library.service.ContactService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan(basePackages = "org.kj6682.library")
@Profile("dev")
public class DevTestConfig {

	@Bean
	public ContactService todoService() {
		return Mockito.mock(ContactService.class);
	}

}// :)

