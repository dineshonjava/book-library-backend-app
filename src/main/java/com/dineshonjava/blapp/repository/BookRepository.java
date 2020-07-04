/**
 * 
 */
package com.dineshonjava.blapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.dineshonjava.blapp.domain.Book;

/**
 * @author dinesh
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {

}
