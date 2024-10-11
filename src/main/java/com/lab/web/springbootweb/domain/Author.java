package com.lab.web.springbootweb.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Author", schema="BOOKSTORE")
public class Author implements Serializable {

	private static final long serialVersionUID = 490390612111963166L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for MySQL
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "AUTHOR_ID_GENERATOR")
//	@SequenceGenerator(name = "AUTHOR_ID_GENERATOR", sequenceName = "AUTHOR_ID_SEQ", allocationSize = 1)
	@Column(name = "author_id", unique = true, nullable = false, precision = 22, scale = 0)
	private Long authorId;

	@Column(name = "name")
	private String name;

	@Column(name = "birthday")
	private LocalDate birthday;
	
	@Column(name = "isbn")
	private String isbn;

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
