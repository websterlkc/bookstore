package com.lab.web.springbootweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lab.web.springbootweb.domain.Author;
import com.lab.web.springbootweb.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
    	try {
        	return authorRepository.findAll();
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }

    public Author getAuthorById(Long id) {
    	try {
    		return authorRepository.findByAuthorId(id);
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }
    
    public List<Author> getAuthorByName(String name) {
    	try {
    		return authorRepository.findByName(name);
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }

    public Author createAuthor(Author author) {
    	try {
    		return authorRepository.save(author);
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }

    public Author updateAuthor(Author author) {
    	try { 
	    	if(author!=null && !StringUtils.isEmpty(author.getAuthorId()) ) {
	    		Author targetAuthor = authorRepository.findByAuthorId(author.getAuthorId());
	    		if(targetAuthor!=null) {
		    		targetAuthor.setName(author.getName());
		    		targetAuthor.setIsbn(author.getIsbn());
		    		targetAuthor.setBirthday(author.getBirthday());
		            return authorRepository.save(targetAuthor);
	    		}
	
	    	}
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
        
        return null;
    }

    public void deleteAuthor(Long id) {
    	try { 
    		authorRepository.deleteById(id);
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }
}
