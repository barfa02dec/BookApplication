package com.app.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.model.BookingDetails;
import com.app.model.OrderDetails;

@Repository
@Transactional
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer>{

	@Query("SELECT u FROM BookingDetails u WHERE u.orderId=:orderId")
	public List<BookingDetails> getAllProductsOfAnOrder(@Param("orderId")OrderDetails orderId); 
	@Modifying
	@Query("DELETE FROM BookingDetails bd WHERE bd.bookingId=:bookingId")
	public int removeBookingDetails(@Param("bookingId")int bookingId);
	

}
