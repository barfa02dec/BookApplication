package com.app.service;

import java.util.List;

import com.app.exception.BookException;
import com.app.model.BookInventory;

public interface BookInventoryInf {
	
	/************* Create ***************/
	public List<BookInventory> addBookInventory(List<BookInventory> bookInventory,int bookId)throws BookException;
	
	
	/************** Read ****************/
	public BookInventory getBookInventoryById(int bookInventory) throws BookException;
	
	
	/**************** Update ******************/
	public BookInventory updateBookInventory(BookInventory bookInventory) throws BookException;
	
}
