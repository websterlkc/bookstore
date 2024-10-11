package com.lab.web.springbootweb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Book", schema="BOOKSTORE")
public class Book implements Serializable {

	private static final long serialVersionUID = 480390612111963166L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for MySQL
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BOOK_ID_GENERATOR")
//	@SequenceGenerator(name = "BOOK_ID_GENERATOR", sequenceName = "BOOK_ID_SEQ", allocationSize = 1)
	@Column(name = "bid", unique = true, nullable = false, precision = 22, scale = 0)
	private Long bid;

	@Column(name = "isbn")
	private String isbn;

	@Column(name = "title")
	private String title;

	@Column(name = "genre")
	private String genre;
	
	@Column(name = "year")
	private int year;

	@Column(name = "price")
	private double price;	

	public Long getBid() {
		return bid;
	}

	public void setBid(Long bid) {
		this.bid = bid;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
