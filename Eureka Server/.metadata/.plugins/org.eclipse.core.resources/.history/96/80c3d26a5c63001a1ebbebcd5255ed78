package com.app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.app.exception.BookException;
import com.app.model.BookInventory;
import com.app.repository.BookInventoryRepository;

@Service
public class BookInventoryImp implements BookInventoryInf{


	@Autowired
	BookInventoryRepository bookInventoryRepository;
	@Autowired
	BookServiceInf bookService;

	/**
	 * Input List of book inventory object and book id
	 * get book object by book id and set in book inventory object
	 * insert all book inventory details into table
	 * return list of book inventory object that stored in database
	 * @throws BookException
	 */
	@Override
	public List<BookInventory> addBookInventory(List<BookInventory> bookInventory,int bookId){
		try {
			for(BookInventory b : bookInventory){
				b.setBookId(bookService.getBookById(bookId));
				bookInventoryRepository.save(b);
			}
			return bookInventory;
		}catch(BookException e) {
			throw e;
		}
	}

	/**
	 * Input book inventory id
	 * return book inventory object 
	 * @throws BookException
	 */
	@Override
	public BookInventory getBookInventoryById(int bookInventory){
		return bookInventoryRepository.findById(bookInventory).orElseThrow(() -> new BookException(HttpStatus.NOT_FOUND,"Invalid book inventory id"));
	}
	/**
	 * input is book inventory object without book id
	 * check object is exist or not and set book id
	 * replace old object by new object
	 * return updated object
	 * @throws BookException
	 */
	@Override
	public BookInventory updateBookInventory(BookInventory bookInventory){
		try{
			BookInventory bi = this.getBookInventoryById(bookInventory.getBookInventoryId());
			bookInventory.setBookId(bi.getBookId());
			return bookInventoryRepository.save(bookInventory);
		}catch (BookException e) {
			throw e;
		}
	}
	
}
