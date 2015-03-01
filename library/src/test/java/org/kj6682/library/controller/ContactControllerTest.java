package org.kj6682.library.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kj6682.library.TestUtil;
import org.kj6682.library.bean.Contact;
import org.kj6682.library.service.ContactService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ContactControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@InjectMocks
	ContactController contactController = new ContactController();

	@Mock
	private ContactService contactServiceMock;

	@Before
	public void setUp() {
	
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
	}

	@Test
	public void findAll__list_with_2_elements() throws Exception {

		Contact first = new Contact(1L, "Isaac", "Newton");
		Contact second = new Contact(2L, "Albert", "Einstein");

		when(contactServiceMock.findAll()).thenReturn(Arrays.asList(first, second));

		mockMvc.perform( get("/contact/list") )
		        .andExpect( status().isOk() )
		        .andExpect( content().contentType(contentType) )
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("Isaac"))).andExpect(jsonPath("$[0].lastName", is("Newton")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].firstName", is("Albert")))
				.andExpect(jsonPath("$[1].lastName", is("Einstein")));
		
		 verify(contactServiceMock, times(1)).findAll();
	     verifyNoMoreInteractions(contactServiceMock);

	}

    @Test
	public void add() throws Exception {
		
		Contact contact = new Contact(1L, "Isaac", "Newton");
		
		when( contactServiceMock.add(any(String.class), any(String.class) ) ).thenReturn(contact);
		
		 
		mockMvc.perform( post("/contact/")
				         .contentType(TestUtil.APPLICATION_JSON_UTF8)
                         .content(TestUtil.convertObjectToJsonBytes(contact))
                         )
                .andExpect( status().isOk() )
                .andExpect( content().contentType(contentType) )
                .andExpect( jsonPath("$.id", is(1)) )
                .andExpect( jsonPath("$.firstName", is("Isaac")) )
                .andExpect( jsonPath("$.lastName", is("Newton")) );
		
		verify(contactServiceMock, times(1)).add(any(String.class), any(String.class));
	    verifyNoMoreInteractions(contactServiceMock);
	}


	@Test
	public void findById__success() throws Exception {

		Contact contact = new Contact(1L, "Isaac", "Newton");
		
		when(contactServiceMock.findById( any(Long.class) )).thenReturn(contact);

		mockMvc.perform( get("/contact/{id}", 1L) )
		        .andExpect( status().isOk() )
		        .andExpect( content().contentType(contentType) )
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is("Isaac")))
				.andExpect(jsonPath("$.lastName", is("Newton")));
		
		verify(contactServiceMock, times(1)).findById(any(Long.class));
	    verifyNoMoreInteractions(contactServiceMock);

	}

	@Test
	public void deleteById__success() throws Exception {

		Contact contact = new Contact(1L, "Isaac", "Newton");
		
		when(contactServiceMock.deleteById( any( Long.class ) )).thenReturn(contact);

		mockMvc.perform( delete("/contact/{id}", 1L) )
		        .andExpect( status().isOk() )
		        .andExpect( content().contentType(contentType) )
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is("Isaac")))
				.andExpect(jsonPath("$.lastName", is("Newton")));
		
		verify(contactServiceMock, times(1)).deleteById(any(Long.class));
	    verifyNoMoreInteractions(contactServiceMock);
	}
}
