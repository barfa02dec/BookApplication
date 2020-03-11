package com.app.service;

import com.app.model.Book;

public interface BookServiceInf {
	/************ Create ***********/
	public Book addBook(Book book);
	
	/************* Read ************/
	public Book getBookById(int bookId);
	public Iterable<Book> getAllBooks();
	
	/************* Update **********/
	public Book updateBook(Book book);
	
}
