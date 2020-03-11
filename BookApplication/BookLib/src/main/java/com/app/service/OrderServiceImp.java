package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import com.app.exception.BookException;
import com.app.model.BookInventory;
import com.app.model.BookingDetails;
import com.app.model.OrderDetails;
import com.app.model.User;
import com.app.repository.BookInventoryRepository;
import com.app.repository.BookingDetailsRepository;
import com.app.repository.OrderDetailsRepository;

@Service
public class OrderServiceImp implements OrderServiceInf{

	@Autowired
	UserServiceInf userService;
	@Autowired
	BookInventoryInf bookInventoryService;
	@Autowired
	OrderDetailsRepository orderRepository;
	@Autowired
	BookingDetailsRepository bookingRepository;
	@Autowired
	BookInventoryRepository bookInventoryRepository;
	@Override
	
	/**
	 * Input user_id as path variable and list of booking details that takes quantity and book inventory id
	 * Check user is valid
	 * find stocks for each book inventory and decrement stock by quantity and update it
	 * get price for each book inventory and add in total amount with quantity
	 * Create object of order table and store it and get order id
	 * store each book inventory id in booking details table
	 * return total amount of order 
	 * @throws BookException
	 * Roll back on book exception
	 */
	@Transactional(rollbackOn = BookException.class)
	public double placeOrder(int userId, List<BookingDetails> bookingDetails) throws BookException{
		try {
			//check user is valid
			User user = userService.getUserById(userId);
			double totalAmount = 0;
			//loop for checking stocks and decrement and update stock in book inventory table
			for(BookingDetails be : bookingDetails) {	
				BookInventory bookInventory = bookInventoryService.getBookInventoryById(be.getBookInventoryId().getBookInventoryId());
				if(bookInventory.getStock()>=be.getQuantity()) {
					bookInventory.setStock(bookInventory.getStock()-be.getQuantity());
					bookInventoryService.updateBookInventory(bookInventory);
				}else
					throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY, "Out of Stock");
				totalAmount += bookInventory.getBookId().getPrice() * be.getQuantity(); 
			}
			//create and store order object and get order id 
			OrderDetails order = orderRepository.save(new OrderDetails(user,totalAmount,LocalDate.now()));
			//loop for store each product detail in booking_details table
			for(BookingDetails bd : bookingDetails) {
				bd.setBookInventoryId(bookInventoryService.getBookInventoryById(bd.getBookInventoryId().getBookInventoryId()));
				bd.setOrderId(order);
				bd.setDeliveryDate(LocalDate.now().plusDays(bd.getBookInventoryId().getDeliveryInDays()));
				bookingRepository.save(bd);
			}	
//			if(true)
//				throw new BookException(HttpStatus.OK, "Is Roll Back Working");
			return totalAmount;
		}catch(BookException e) {
			throw e;
		}
	}
	/**
	 * input order id
	 * return Order details
	 * @throw BookException
	 */
	@Override
	public OrderDetails findOrder(int orderId) throws BookException{
		return orderRepository.findById(orderId).orElseThrow(()-> new BookException(HttpStatus.NOT_FOUND,"Order not found for order id "+orderId));
	}
	/**
	 * input order id
	 * return product details for order id
	 * @throws BookException
	 */
	@Override
	public List<BookingDetails> getAllProductOfAnOrder(int orderId) throws BookException {
		return bookingRepository.getAllProductsOfAnOrder(this.findOrder(orderId));
	}
	
	/**
	 * input user id
	 * get list of orders where user id = user id
	 * for each order id get list of products from booking table and add into list
	 * return list of list of products
	 * @throws BookException
	 */
	@Override
	public List<List<BookingDetails>> getAllProductOfAnUser(int userId) throws BookException {
			List<OrderDetails> orders = orderRepository.getAllOrdersOfAnUser(userService.getUserById(userId));
			if(orders.isEmpty())
				throw new BookException(HttpStatus.NOT_FOUND,"No order placed for user id "+userId);
			List<List<BookingDetails>> products = new ArrayList<>();
		for(OrderDetails o : orders) 
			products.add(this.getAllProductOfAnOrder(o.getOrderId()));
		return products;
	}
	
	/**
	 * Input order_id
	 * get all products of order
	 * get quantity of product and update stock in book inventory table
	 * remove the products from booking details table
	 * remove order from order details table
	 * return 
	 * @throws BookException
	 */
	@Transactional(rollbackOn = BookException.class)
	@Override
	public void removeOrder(int orderId) throws BookException {
		List<BookingDetails> products = bookingRepository.getAllProductsOfAnOrder(this.findOrder(orderId));
		for(BookingDetails product : products) {
			int quantity = product.getQuantity();
			BookInventory bi = product.getBookInventoryId();
			bi.setStock(bi.getStock()+quantity);
			bookInventoryRepository.save(bi);
		}
//		if(true)
//			throw new BookException(HttpStatus.ACCEPTED, "Check Roll back");
		for(BookingDetails product : products)
			bookingRepository.removeBookingDetails(product.getBookingId());
		orderRepository.removeOrder(orderId);
	}
	
	/**
	 * input user id
	 * get all order of user and remove all orders
	 * return 
	 * @throws BookException
	 */
	@Override
	@Transactional(rollbackOn = BookException.class)
	public void removeAllOrderOfAnUser(int userId) throws BookException {
		User user = userService.getUserById(userId);
		List<OrderDetails> orders = orderRepository.getAllOrdersOfAnUser(user);
		if(orders.isEmpty())
			throw new BookException(HttpStatus.NOT_FOUND,"No order placed for user id "+userId);
		for(OrderDetails order : orders)
			this.removeOrder(order.getOrderId());
	}
		
}
