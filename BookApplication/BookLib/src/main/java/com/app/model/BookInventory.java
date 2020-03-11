package com.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.app.validation.ValidateBookDetails;

@Entity
@Table(name = "bookInventory")
public class BookInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookInventoryId;
	@NotNull
	private int stock;
	@NotNull
	private String language;
	@NotNull
	private int deliveryInDays;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn( name = "bookId"/*,foreignKey = @ForeignKey( name = "book_id")*/)
	private Book bookId;
	
	public BookInventory() {
		super();
	}

	public BookInventory(@NotNull int stock, @NotNull String language, @NotNull int deliveryInDays) {
		super();
		ValidateBookDetails.checkNullValue(stock, "stock");
		this.stock = stock;
		ValidateBookDetails.checkNullValue(language, "language");
		this.language = language;
		ValidateBookDetails.checkNullValue(deliveryInDays, "deliveryInDays");
		this.deliveryInDays = deliveryInDays;
	}


	public Integer getBookInventoryId() {
		return bookInventoryId;
	}

	public void setBookInventoryId(Integer bookInventoryId) {
		ValidateBookDetails.checkNullValue(bookInventoryId, "bookInventoryId");
		this.bookInventoryId = bookInventoryId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		ValidateBookDetails.checkNullValue(stock, "stock");
		this.stock = stock;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		ValidateBookDetails.checkNullValue(language, "language");
		this.language = language;
	}

	public int getDeliveryInDays() {
		return deliveryInDays;
	}

	public void setDeliveryInDays(int deliveryInDays) {
		ValidateBookDetails.checkNullValue(deliveryInDays, "deliveryInDays");
		this.deliveryInDays = deliveryInDays;
	}

	public Book getBookId() {
		return bookId;
	}

	public void setBookId(Book bookId) {
		ValidateBookDetails.checkNullValue(bookId, "bookId");
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return "BookInventory [bookInventoryId=" + bookInventoryId + ", stock=" + stock + ", language=" + language
				+ ", deliveryInDays=" + deliveryInDays + ", bookId=" + bookId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((bookInventoryId == null) ? 0 : bookInventoryId.hashCode());
		result = prime * result + deliveryInDays;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + stock;
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
		BookInventory other = (BookInventory) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (bookInventoryId == null) {
			if (other.bookInventoryId != null)
				return false;
		} else if (!bookInventoryId.equals(other.bookInventoryId))
			return false;
		if (deliveryInDays != other.deliveryInDays)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}
	
	
}
