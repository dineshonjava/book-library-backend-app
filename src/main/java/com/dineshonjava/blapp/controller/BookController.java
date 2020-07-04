/**
 * 
 */
package com.dineshonjava.blapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dineshonjava.blapp.domain.Book;
import com.dineshonjava.blapp.repository.BookRepository;
import com.dineshonjava.blapp.repository.LibraryRepository;

/**
 * @author dinesh
 *
 */
@RestController
@RequestMapping("/books")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@GetMapping(value = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Book byISBN(@PathVariable Long isbn) {
		
		return bookRepository.findById(isbn).get();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Book create(@RequestBody Book book) {
		
		return bookRepository.save(book);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Book> all(){
		
		return bookRepository.findAll();
	}
	
	@PutMapping(value = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Book update(@PathVariable Long isbn, @RequestBody Book book) {
		
		return bookRepository.save(book);
	}
	
	@GetMapping(value = "/lib/{libid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> allBookForALibrary(@PathVariable Long libid){
		
		return libraryRepository.findById(libid).isPresent() ? libraryRepository.findById(libid).get().getBooks() : new ArrayList<>();
	}
	
	
}
