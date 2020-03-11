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
import com.app.validation.ValidateBookDetails;

@Entity
@Table(name = "bookingDetails")
public class BookingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	@NotNull
	private LocalDate deliveryDate;
	@NotNull
	private int quantity;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "bookInventoryId",foreignKey = @ForeignKey(name = "bookInventoryId"))
	private BookInventory bookInventoryId;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId")
	private OrderDetails orderId;
	
	public BookingDetails() {
		super();
	}
	

	public BookingDetails(int quantity, BookInventory bookInventoryId) {
		super();
		ValidateBookDetails.checkNullValue(quantity, "quantity");
		this.quantity = quantity;
		ValidateBookDetails.checkNullValue(bookInventoryId, "bookInventoryId");
		this.bookInventoryId = bookInventoryId;
	}


	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public BookInventory getBookInventoryId() {
		return bookInventoryId;
	}

	public void setBookInventoryId(BookInventory bookInventoryId) {
		this.bookInventoryId = bookInventoryId;
	}

	public OrderDetails getOrderId() {
		return orderId;
	}

	public void setOrderId(OrderDetails orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "BookingDetails [bookingId=" + bookingId + ", deliveryDate=" + deliveryDate + ", quantity="
				+ quantity + ", bookInventoryId=" + bookInventoryId + ", orderId=" + orderId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookInventoryId == null) ? 0 : bookInventoryId.hashCode());
		result = prime * result + bookingId;
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + quantity;
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
		BookingDetails other = (BookingDetails) obj;
		if (bookInventoryId == null) {
			if (other.bookInventoryId != null)
				return false;
		} else if (!bookInventoryId.equals(other.bookInventoryId))
			return false;
		if (bookingId != other.bookingId)
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
}
