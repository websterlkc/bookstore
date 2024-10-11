package com.lab.web.springbootweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.web.springbootweb.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	public Book findByIsbn(String isbn);
	
	public List<Book> findByTitle(String title);
}
