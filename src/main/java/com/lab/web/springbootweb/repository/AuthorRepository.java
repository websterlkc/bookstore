package com.lab.web.springbootweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.web.springbootweb.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	public Author findByAuthorId(Long authorId);

	public List<Author> findByIsbn(String isbn);
	
	public List<Author> findByName(String name);
	
}
