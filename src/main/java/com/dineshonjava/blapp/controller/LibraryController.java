/**
 * 
 */
package com.dineshonjava.blapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dineshonjava.blapp.domain.Library;
import com.dineshonjava.blapp.repository.LibraryRepository;

/**
 * @author dinesh
 *
 */

@RestController
@RequestMapping("/libraries")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
public class LibraryController {
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Library create(@RequestBody Library library) {
		
		return libraryRepository.save(library);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Library> all(){
		System.out.println("Libraries.....");
		return libraryRepository.findAll();
	}
	
	@GetMapping(value = "/{libid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Library byId(@PathVariable Long libid) {
		
		return libraryRepository.findById(libid).get();
	}
}
