package com.example.demo.beans;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.enums.CouponType;



/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Entity(name = "COUPON")
public class Coupon {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "TITLE")
	private String title;
	
	@Column (name = "START_DATE")
	private Date startDate;
	
	@Column (name = "END_DATE")
	private Date endDate;
	
	@Column (name = "AMOUNT")
	private int amount;
	
	@Column (name = "TYPE")
	@Enumerated(EnumType.STRING)
	private CouponType type;
	
	@Column (name = "MESSAGE")
	private String message;
	
	@Column (name = "PRICE")
	private double price;
	
	@Column (name = "IMAGE")
	private String image;
	
	

	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(name = "Customer_Coupon", joinColumns = @JoinColumn(name = "coupon_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))	
	private List<Customer> customers;
	
	public Coupon() {
		
	}
	/**
	 * 
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param type
	 * @param message
	 * @param price
	 * @param image
	 */
	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType type, String message,
			double price, String image) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}


	/**
	 * @return  id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return  title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param  title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return  type
	 */
	public CouponType getType() {
		return type;
	}

	/**
	 * @param type to set
	 */
	public void setType(CouponType type) {
		this.type = type;
	}

	/**
	 * @return  message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return  price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return  image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers to set
	 */
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + ", customers=" + customers.size() + "]";
	}

	
	
	
	
}
