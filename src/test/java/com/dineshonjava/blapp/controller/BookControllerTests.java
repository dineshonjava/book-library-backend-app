/**
 * 
 */
package com.dineshonjava.blapp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dineshonjava.blapp.domain.Book;
import com.dineshonjava.blapp.domain.Library;
import com.dineshonjava.blapp.repository.BookRepository;
import com.dineshonjava.blapp.repository.LibraryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author dinesh
 *
 */
@WebMvcTest(controllers = BookController.class)
public class BookControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookRepository bookRepository;
	
	@MockBean
	private LibraryRepository libraryRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		when(bookRepository.findById(10000l)).thenReturn(Optional.of(findByISBN()));
		when(libraryRepository.findById(50000l)).thenReturn(Optional.of(findByLibId()));
		when(bookRepository.findAll()).thenReturn(findAllBooks());
		when(libraryRepository.findAll()).thenReturn(findAllLibraries());
		
	}
	
	@Test
	public void testCreatBook() throws Exception {
		Book book = new Book(30000l, "Hands-on microservices", "Java", "Dinesh Rajput", "Packt Publication");
		String inputJson = mapToJson(book);

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/books").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(201, status);
		
	}
	
	@Test
	public void testUpdateBook() throws Exception {
	   String uri = "/books/10000";
	   Book book = new Book();
	   book.setIsbn(10000l);
	   book.setTitle("Mastering Spring Boot");
	   
	   String inputJson = mapToJson(book);
	   
	   MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);

	}
	
	@Test
	public void shouldFetchAllBooks() throws Exception {
		
		this.mockMvc.perform(get("/books"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(2)));
	}
	
	@Test
	public void shouldFetchOneBookByISBN() throws Exception {
		
		final Long isbn = 10000l;
		
		this.mockMvc.perform(get("/books/{isbn}" , isbn))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title", is("Spring 5 Design Patterns")))
		.andExpect(jsonPath("$.author", is("Dinesh Rajut")));
	}
	
	@Test
	public void shouldFetchAllBooksOfALibrary() throws Exception {
		
		final Long libId = 50000l;
		
		this.mockMvc.perform(get("/books/lib/{libid}" , libId))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(2)));
	}
	
	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
	
	private Iterable<Book> findAllBooks(){
		List<Book> books = new ArrayList<>();
		books.add(new Book(10000l, "Spring 5 Design Patterns", "Java", "Dinesh Rajut", "Packt Publication"));
		books.add(new Book(20000l, "Mastering in Spring Boot", "Java", "Dinesh Rajut", "Packt Publication"));
		return books;
	}
	
	private Iterable<Library> findAllLibraries(){
		
		List<Library> libraries = new ArrayList<>();
		Library library = new Library(50000l, "Computer", "Java", "Alex");
		
		library.getBooks().add(new Book(10000l, "Spring 5 Design Patterns", "Java", "Dinesh Rajut", "Packt Publication"));
		library.getBooks().add(new Book(20000l, "Mastering in Spring Boot", "Java", "Dinesh Rajut", "Packt Publication"));
		
		libraries.add(library);
		library = new Library(60000l, "Micsroservices", "Software", "Jazz");
		
		library.getBooks().add(new Book(30000l, "Hands-on Microservices", "Java", "Dinesh Rajut", "Packt Publication"));
		libraries.add(library);
		
		
		return libraries;
	}
	
	private Library findByLibId(){
		Library library = new Library(50000l, "Computer", "Java", "Alex");
		library.getBooks().add(new Book(10000l, "Spring 5 Design Patterns", "Java", "Dinesh Rajut", "Packt Publication"));
		library.getBooks().add(new Book(20000l, "Mastering in Spring Boot", "Java", "Dinesh Rajut", "Packt Publication"));
		return library;
	}
	
	private Book findByISBN(){
		return new Book(10000l, "Spring 5 Design Patterns", "Java", "Dinesh Rajut", "Packt Publication");
	}
}
