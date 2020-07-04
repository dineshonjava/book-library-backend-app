/**
 * 
 */
package com.dineshonjava.blapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dinesh
 *
 */
@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ISBN", nullable = false)
	private Long isbn;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "TYPE", nullable = false)
	private String type;
	
	@Column(name = "AUTHOR", nullable = false)
	private String author;
	
	@Column(name = "PUBLISHER", nullable = false)
	private String publisher;

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Book() {
		super();
	}

	public Book(Long isbn, String title, String type, String author, String publisher) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.type = type;
		this.author = author;
		this.publisher = publisher;
	}
	
}
