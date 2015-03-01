package org.kj6682.library.controller;

import java.util.List;

import org.kj6682.library.bean.Contact;
import org.kj6682.library.exception.ContactNotFoundException;
import org.kj6682.library.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;

@Api(value = "contact", description = "contacts of a simple library application") 
@RestController
@RequestMapping("/contact")
public class ContactController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Contact> findAll() {
		List<Contact> result = service.findAll();
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Contact add(
			@RequestParam(value = "firstName", defaultValue = "NAME") String firstName,
			@RequestParam(value = "lastName", defaultValue = "SURMAME") String lastName) {

		LOGGER.debug("Adding a new Contact entry with information: {} {} ", firstName, lastName);

		Contact added = service.add(firstName, lastName);

		LOGGER.debug("Added a Contact entry with information: {}", added);

		return added;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Contact findById(@PathVariable("id") Long id) throws ContactNotFoundException {
		
		LOGGER.debug("Searching for a Contact entry with id: {}", id);

		Contact contact = service.findById(id);

		LOGGER.debug("Found Contact entry with information: {}", contact);

		return contact;
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Contact deleteById(@PathVariable("id") Long id) throws ContactNotFoundException {
		LOGGER.debug("Deleting a Contact entry with id: {}", id);

		Contact deleted = service.deleteById(id);

		LOGGER.debug("Deleted Contact entry with information: {}", deleted);

		return deleted;
	}

}// :)

