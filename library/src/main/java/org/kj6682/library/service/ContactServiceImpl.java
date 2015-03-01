package org.kj6682.library.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.kj6682.library.bean.Contact;

public class ContactServiceImpl implements ContactService {

	private final AtomicLong counter = new AtomicLong();
	
	@Override
	public List<Contact> findAll() {
		Contact first = new Contact(1L, "Isaac", "Newton");
		Contact second = new Contact(2L, "Albert", "Einstein");

		return (Arrays.asList(first, second));

	}

	@Override
	public Contact add(String firstName, String lastName) {
		return new Contact(counter.incrementAndGet(), firstName, lastName);
	}

	@Override
	public Contact findById(Long id) {
		return new Contact(id, "firstName", "lastName");
	}

	@Override
	public Contact deleteById(Long id) {
		return new Contact(id, "firstName", "lastName");
	}

}
