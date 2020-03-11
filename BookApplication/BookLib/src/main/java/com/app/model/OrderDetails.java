package com.app.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "orderDetails")
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "userId",foreignKey = @ForeignKey(name = "userId"))
	private User userId;
	@NonNull
	private double totalAmount;
	@NotNull
	private LocalDate bookingDate;

	public OrderDetails() {
		super();
	}

	public OrderDetails(User userId, double totalAmount, @NotNull LocalDate bookingDate) {
		super();
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.bookingDate = bookingDate;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", userId=" + userId + ", totalAmount=" + totalAmount
				+ ", bookingDate=" + bookingDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetails other = (OrderDetails) obj;
		if (bookingDate == null) {
			if (other.bookingDate != null)
				return false;
		} else if (!bookingDate.equals(other.bookingDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (Double.doubleToLongBits(totalAmount) != Double.doubleToLongBits(other.totalAmount))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
}
