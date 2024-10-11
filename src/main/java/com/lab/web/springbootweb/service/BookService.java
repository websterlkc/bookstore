package com.lab.web.springbootweb.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lab.web.springbootweb.domain.Book;
import com.lab.web.springbootweb.repository.AuthorRepository;
import com.lab.web.springbootweb.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private AuthorRepository authorRepository;    

    public List<Book> getAllBooks() {
        
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }
    
    

    public Book getBookById(String isbn) {    
        try {
            return bookRepository.findByIsbn(isbn);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    }

    public Book createBook(Book book) {
    	try {
	    	if(book!=null && !StringUtils.isEmpty(book.getIsbn()) ) {
	    		Book targetBook = bookRepository.findByIsbn(book.getIsbn());
	    		if(targetBook==null) {
	    			return bookRepository.save(book);
	    		}
	    	}
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    	
    	return null;
    }

    public Book updateBook(Book book) {    	
    	try {
	    	if(book!=null && !StringUtils.isEmpty(book.getIsbn()) ) {
	    		Book targetBook = bookRepository.findByIsbn(book.getIsbn());
	    		if(targetBook!=null) {
	                targetBook.setTitle(book.getTitle());
	                targetBook.setGenre(book.getGenre());
	                targetBook.setYear(book.getYear());
	                targetBook.setPrice(book.getPrice());
		            return bookRepository.save(targetBook);
	    		}
	    	}
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    	
    	return null;
    }

    public String deleteBook(Book book) {
        if(book==null || StringUtils.isEmpty(book.getIsbn())) {
    		return null;
    	}
        
    	try {
	        Book targetBook = bookRepository.findByIsbn(book.getIsbn());
	        if (targetBook != null) {
	        	bookRepository.delete(targetBook);
	        	return book.getIsbn();
	        }        
    	} catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage(), e);
        }
    	
        return null;            
    }
}
