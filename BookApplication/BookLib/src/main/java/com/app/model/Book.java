package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.app.validation.ValidateBookDetails;

@Entity
@Table(name = "book")
/**
 * Class that contains Book details
 * */
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookId",nullable = false)
	private Integer bookId;
	@NotNull
	@Column(unique = true)
	private String name;
	@NotNull
	private float rating;
	@NotNull
	private String description;
	@NotNull
	private String author;
	@NotNull
	private double price;
	@NotNull
	private String publisher;
	
	public Book() {
		super();
	}
	
	public Book(String name, float rating, String description, String author,
			double price, String publisher) {
		super();
		ValidateBookDetails.checkNullValue(name,"name");
		this.name = name;
		ValidateBookDetails.checkNullValue(rating,"rating");
		this.rating = rating;
		ValidateBookDetails.checkNullValue(description,"description");
		this.description = description;
		ValidateBookDetails.checkNullValue(author,"author");
		this.author = author;
		ValidateBookDetails.checkNullValue(price,"price");
		this.price = price;
		ValidateBookDetails.checkNullValue(publisher,"publisher");
		this.publisher = publisher;
	}
	

	public Book(Integer bookId, String name, float rating, String description,
			String author, double price, String publisher) {
		this(name,rating,description,author,price,publisher);
		ValidateBookDetails.checkNullValue(bookId,"bookId");
		this.bookId = bookId;
		
	}


	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		ValidateBookDetails.checkNullValue(bookId,"bookId");
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		ValidateBookDetails.checkNullValue(name,"name");
		this.name = name;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		ValidateBookDetails.checkNullValue(rating,"rating");
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		ValidateBookDetails.checkNullValue(description,"description");
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		ValidateBookDetails.checkNullValue(author,"author");
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		ValidateBookDetails.checkNullValue(price,"price");
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		ValidateBookDetails.checkNullValue(publisher,"publisher");
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", rating=" + rating + ", description=" + description
				+ ", author=" + author + ", price=" + price + ", publisher=" + publisher + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + Float.floatToIntBits(rating);
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
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (Float.floatToIntBits(rating) != Float.floatToIntBits(other.rating))
			return false;
		return true;
	}

}
