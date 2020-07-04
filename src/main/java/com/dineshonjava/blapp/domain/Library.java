/**
 * 
 */
package com.dineshonjava.blapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author dinesh
 *
 */
@Entity
@Table(name = "LIBRARY")
public class Library implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "LIBID", nullable = false)
	private Long libId;
	
	@Column(name = "LIBNAME", nullable = false)
	private String libName;
	
	@Column(name = "LIBTYPE", nullable = false)
	private String libType;
	
	@Column(name = "LIBRARIAN", nullable = false)
	private String librarian;
	
	@OneToMany
    @JoinTable(name="LIBRARY_BOOK",
                joinColumns=@JoinColumn(name="ISBN"),
                inverseJoinColumns=@JoinColumn(name="LIBID")) 
	private List<Book> books = new ArrayList<>();

	public Long getLibId() {
		return libId;
	}

	public void setLibId(Long libId) {
		this.libId = libId;
	}

	public String getLibName() {
		return libName;
	}

	public void setLibName(String libName) {
		this.libName = libName;
	}

	public String getLibType() {
		return libType;
	}

	public void setLibType(String libType) {
		this.libType = libType;
	}

	public String getLibrarian() {
		return librarian;
	}

	public void setLibrarian(String librarian) {
		this.librarian = librarian;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Library() {
		super();
	}

	public Library(Long libId, String libName, String libType, String librarian) {
		super();
		this.libId = libId;
		this.libName = libName;
		this.libType = libType;
		this.librarian = librarian;
	}
	

}
