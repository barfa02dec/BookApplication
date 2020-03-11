package com.app.controller;

import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.exception.BookException;
import com.app.model.Book;
import com.app.model.BookInventory;
import com.app.service.BookInventoryInf;
import com.app.service.BookServiceInf;

/**
 * Controller that performs CRUD operation that related to Book
 * Work as navigator between model and view
 * */
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@Produces(MediaType.APPLICATION_XML)
public class BookController {

	@Autowired
	BookServiceInf bookService;
	@Autowired
	BookInventoryInf bookInventoryService;
	/**
	 * input book object check validation on each field
	 * insert book object in book table
	 * @return stored object
	 * @throws BookException
	 */
	@PostMapping("/book")
	public @ResponseBody Book addBook(@RequestBody Book book) throws BookException {
		return bookService.addBook(book);
	}
	/**
	 * input book id and list of book inventory
	 * check is book id exist
	 * store list of book inventory into book inventory table
	 * return list of book inventory object 
	 * @throws BookException
	 */

	@PostMapping("/book/inventory/{bookId}")
	public @ResponseBody List<BookInventory> addBookInventory(@PathVariable("bookId")int bookId,@RequestBody List<BookInventory> bookInventory) throws BookException {
		return bookInventoryService.addBookInventory(bookInventory,bookId);
	}
	/**
	 * @return All books
	 */
	
	@GetMapping("/book")
	public @ResponseBody Iterable<Book> getAllBooks(){
		return bookService.getAllBooks(); 
	}
	/**
	 * input book id 
	 * @return book object
	 */
	@GetMapping("/book/{bookId}")
	public Book getBookById(@PathVariable("bookId")int bookId){
		System.out.println(bookId);
		return bookService.getBookById(bookId); 
	}
	/**
	 * input book inventory id
	 * @return book inventory object
	 */
	@GetMapping("/book/inventory/{bookInventoryId}")
	public BookInventory getBookInventoryById(@PathVariable("bookInventoryId")int bookInventoryId){
		return bookInventoryService.getBookInventoryById(bookInventoryId); 
	}
	/**
	 * @param Book object
	 * replace old book object by new object
	 * @return updated book object
	 * @throws BookException
	 */
	@PutMapping("/book")
	public @ResponseBody Book updateBook(@RequestBody Book book) throws BookException {
		return bookService.updateBook(book);
	}
	/**
	 * @param book_inventory
	 * @return updated book inventory object
	 * @throws BookException
	 */
	@PutMapping("/book/inventory")
	public @ResponseBody BookInventory updateBookInventory(@RequestBody BookInventory bookInventory) throws BookException {
		return bookInventoryService.updateBookInventory(bookInventory);
	}
}
