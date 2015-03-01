package org.kj6682.library.service;

import java.util.List;

import org.kj6682.library.bean.Contact;

public interface ContactService {

	List<Contact> findAll();

	Contact add(String firstName, String lastName);

	Contact findById(Long id);

	Contact deleteById(Long id);

}
