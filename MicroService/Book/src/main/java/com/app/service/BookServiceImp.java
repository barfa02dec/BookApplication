package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.app.exception.BookException;
import com.app.model.Book;
import com.app.repository.BookInventoryRepository;
import com.app.repository.BookRepository;

@Service
public class BookServiceImp implements BookServiceInf{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookInventoryRepository bookInventoryRepository;
	/**
	 * input book object
	 * check duplicate name of book and save book object in book table
	 * return saved object
	 * @throw BookException
	 */
	@Override
	public Book addBook(Book book){
		try {
			return bookRepository.save(book);
		}catch(DataIntegrityViolationException e) {
			throw new BookException(HttpStatus.NOT_ACCEPTABLE, "Name already exist");
		}catch(Exception e) {
			e.printStackTrace();
			throw new BookException(HttpStatus.NOT_ACCEPTABLE,"check");
		}
	}

	/**
	 * Input Book object
	 * check book id is exist or not
	 * replace old object by new object except name
	 * return updated object
	 * @throw BookException
	 */
	@Override
	public Book updateBook(Book book) {
		try{
			book.setName(this.getBookById(book.getBookId()).getName());
			return bookRepository.save(book);
		}catch(BookException be) {
			throw be;
		}
	}

	/**
	 * Input book id
	 * return book object
	 * @throw BookException
	 */
	@Override
	public Book getBookById(int bookId) {
		return bookRepository.findById(bookId).orElseThrow(() -> new BookException(HttpStatus.NOT_FOUND,"Invalid book"));
	}
	/**
	 * return all books available in book table
	 */
	@Override
	public Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	

}
