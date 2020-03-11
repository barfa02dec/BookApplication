package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.exception.BookException;
import com.app.model.BookingDetails;
import com.app.service.OrderServiceInf;

/**
 * Controller that performs CRUD operation that related to Book and user
 * Work as navigator between model and view
 * */
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
public class OrderController {
	@Autowired
	OrderServiceInf orderService;
	/**
	 * Input user_id as path variable and list of booking details that takes quantity and book inventory id
	 * check stocks for each book and update book inventory table
	 * calculate total amount and create and store object into order table
	 * get order id and set for each booking details object and store it into booking details table
	 * return total amount of order
	 * @throw BookException
	 */
	@PostMapping("/user/order/{userId}")
	public @ResponseBody double placeOrder(@PathVariable("userId")int userId,@RequestBody List<BookingDetails> bookingDetails) throws BookException{
		if(bookingDetails.isEmpty())
			throw new BookException(HttpStatus.UNPROCESSABLE_ENTITY, "Can not place order with zero product");
		return orderService.placeOrder(userId, bookingDetails);
	}
	
	/**
	 * @param order_id
	 * @return List of product store in booking details based on input order id
	 * @throw BookException
	 */
	@GetMapping("/user/order/{orderId}")
	public @ResponseBody List<BookingDetails> getAllProductsOfAnOrder(@PathVariable("orderId")int orderId) throws BookException{
		return orderService.getAllProductOfAnOrder(orderId);
	}
	
	/**
	 * @param user_id
	 * get list of order id for user_id
	 * get list of product from booking details for each order
	 * @return list of list of booking details
	 */
	@GetMapping("/user/orders/{userId}")
	public @ResponseBody List<List<BookingDetails>> getAllOrdersOfAnUser(@PathVariable("userId") int userId) throws BookException{
		return orderService.getAllProductOfAnUser(userId);
	}
	/**
	 * @param order_id
	 * remove data from order detail table and from booking detail table
	 * update book inventory table
	 * return
	 * @throws BookException
	 */
	@DeleteMapping("/user/order/{orderId}")
	public void removeOrder(@PathVariable("orderId")int orderId) throws BookException{
		orderService.removeOrder(orderId);
	}
	/**
	 * @param user_id
	 * remove all orders of user from order table and booking details table
	 * update book inventory table
	 * return 
	 * @throws BookException
	 */
	@DeleteMapping("/user/orders/{userId}")
	public void removeAllOrdersOfUser(@PathVariable("userId")int userId) throws BookException{
		orderService.removeAllOrderOfAnUser(userId);
	}
}
