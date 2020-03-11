package com.app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.model.OrderDetails;
import com.app.model.User;

@Transactional
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer>{
	/**
	 * @param user
	 * @return list of orders
	 */
	@Query("SELECT o FROM OrderDetails o WHERE o.userId=:userId")
	public List<OrderDetails> getAllOrdersOfAnUser(@Param("userId")User user);	
	@Modifying//Allow to change DDL
	@Query("DELETE FROM OrderDetails od WHERE od.orderId=:orderId")
	public int removeOrder(@Param("orderId")int orderId);
}
