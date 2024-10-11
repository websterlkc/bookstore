package com.lab.web.springbootweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lab.web.springbootweb.domain.Author;
import com.lab.web.springbootweb.domain.Book;
import com.lab.web.springbootweb.service.AuthorService;
import com.lab.web.springbootweb.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController extends BaseController {
	
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Value("${delete.permission.role}")
    private String userRoleAllowDelete;

    @Autowired
    private BookService bookService;
    
    @Autowired
    private AuthorService authorService;

    // Get all books
    @GetMapping("/getAllBooks")
    public @ResponseBody ResponseEntity<Map<String, Object>> getAllBooks(HttpServletRequest request) {    	
    	 if (!authenticate(request)) {
             return new ResponseEntity<>(createResponse("Invalid request", null), HttpStatus.FORBIDDEN);
         }
    	 
        Map<String, Object> response = new HashMap<>();
        List<Book> books = bookService.getAllBooks();
        response.put("books", books);
        response.put("message", "Books retrieved successfully.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get a book by ISBN using a query parameter
    @GetMapping("/getIsbn")
    public @ResponseBody ResponseEntity<Map<String, Object>> getBookByIsbn(HttpServletRequest request, @RequestParam("isbn") String isbn) {
    	if (!authenticate(request)) {
            return new ResponseEntity<>(createResponse("Invalid request", null), HttpStatus.FORBIDDEN);
        }
    	
        Map<String, Object> response = new HashMap<>();
        Book book = bookService.getBookById(isbn);
        if (book != null) {
            response.put("book", book);
            response.put("message", "Book retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Book not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    // Get a book by ISBN using a query parameter
    @GetMapping("/searchTitleAndAuthor")
	public @ResponseBody ResponseEntity<Map<String, Object>> getBookByTitleAndOrAuthorName(
	        HttpServletRequest request,
	        @RequestParam(value = "title", required = false) String title,  // `required = false` makes it optional
	        @RequestParam(value = "author_name", required = false) String authorName) {

    	if (!authenticate(request)) {
            return new ResponseEntity<>(createResponse("Invalid request", null), HttpStatus.FORBIDDEN);
        }
    	
        Map<String, Object> response = new HashMap<>();
    	if(StringUtils.isEmpty(title) && StringUtils.isEmpty(authorName)) {
    		response.put("message", "Missing Params");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	    	
        List<Book> bookList = bookService.getBookByTitle(title);        
        
        List<Author> authorList = authorService.getAuthorByName(authorName);
        List<Book> newBookList = new ArrayList<Book>();
        if(authorList!=null && !authorList.isEmpty()) {
        	for(Author author : authorList) {
        		newBookList.addAll(author.getBooks());
        	}
        	
            if(newBookList!=null && !newBookList.isEmpty()) {
            	bookList.addAll(newBookList);
            }
        }
        
        bookList = bookList.stream().collect(Collectors.toMap(
            Book::getBid,     // Use `bid` as the key
            book -> book,     // The value is the book object itself
            (existing, replacement) -> existing)) // If there's a duplicate, keep the existing one
        		.values().stream().collect(Collectors.toList());

        if (bookList != null) {
            response.put("bookList", bookList);
            response.put("message", "Book retrieved successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Book not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/addBook")
    public @ResponseBody ResponseEntity<Map<String, Object>> addBook(HttpServletRequest request, @RequestBody Book book) {
    	if (!authenticate(request)) {
            return new ResponseEntity<>(createResponse("Invalid request", null), HttpStatus.FORBIDDEN);
        }
    	
        Map<String, Object> response = new HashMap<>();
               
        Book createdBook = bookService.createBook(book);
        
        if(StringUtils.isEmpty(createdBook)) {
        	response.put("message", "Book unable to created.");
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Return 204 if successful
        }else {
            response.put("book", createdBook);
            response.put("message", "Book created successfully.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }


    @PostMapping("/updateBook")
    public @ResponseBody ResponseEntity<Map<String, Object>> updateBook(HttpServletRequest request, @RequestBody Book book) {
    	if (!authenticate(request)) {
            return new ResponseEntity<>(createResponse("Invalid request", null), HttpStatus.FORBIDDEN);
        }
    	
        Map<String, Object> response = new HashMap<>();
        Book updatedBook = bookService.updateBook(book);
        if (updatedBook != null) {
            response.put("book", updatedBook);
            response.put("message", "Book updated successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Book not found for update.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }        
    }

    // Delete a book by ISBN using a POST request
    @PostMapping("/delete")
    public @ResponseBody ResponseEntity<Map<String, Object>> deleteBook(HttpServletRequest request, @RequestBody Book book) {
    	if (!authenticate(request)) {
            return new ResponseEntity<>(createResponse("Invalid request", null), HttpStatus.FORBIDDEN);
        }
    	
        Map<String, Object> response = new HashMap<>();

        HttpSession session = request.getSession();
        String authUserRole = (String) session.getAttribute("AUTH_USER_ROLE");
        logger.info("authUserRole "+authUserRole);
        logger.info("userRoleAllowDelete "+userRoleAllowDelete);

        if(authUserRole==null || StringUtils.isEmpty(authUserRole)) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        if(userRoleAllowDelete.equalsIgnoreCase(authUserRole)) {
            String isbn = bookService.deleteBook(book);
            
            if(StringUtils.isEmpty(isbn)) {
            	response.put("message", "Book not found for delete.");
    	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Return 204 if successful
            }else {
    	        response.put("message", "Book deleted successfully.");
    	        return new ResponseEntity<>(response, HttpStatus.OK); // Return 204 if successful
            }
        }else {
        	response.put("message", "Unauthorized User Request for deletion.");
        	return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}
