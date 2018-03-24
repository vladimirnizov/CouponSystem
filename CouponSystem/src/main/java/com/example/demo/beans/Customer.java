package com.example.demo.beans;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Entity(name = "CUSTOMER")
public class Customer {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "Customer_Name")
	private String customertName;
	
	@Column (name = "Password")
	private String pass;

	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.DETACH , CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(name = "Customer_Coupon",
				joinColumns = @JoinColumn(name = "Customer_ID"),
				inverseJoinColumns = @JoinColumn(name = "Coupon_ID"))
	private Collection<Coupon> coupons;

	public Customer() {
		
	}
	
	
	
	/**
	 * @param customertName
	 * @param pass
	 */
	public Customer(String customertName, String pass) {
		super();
		this.customertName = customertName;
		this.pass = pass;
	}



	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param  id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return customerName
	 */
	public String getCustomerName() {
		return customertName;
	}

	/**
	 * @param  customerName to set
	 */
	public void setCustomerName(String custName) {
		this.customertName = custName;
	}

	/**
	 * @return pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return coupons
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}


	/**
	 * @param coupons to set
	 */
	public void setCoupons(Collection<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 * 
	 * @param add coupons to customer
	 */
	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}
	
	/**
	 * 
	 * @param remove coupon from customer
	 */
	public void removeCoupon(Coupon coupon) {
		for(Coupon coup : this.coupons) {
			if(coup.getId()==coupon.getId()) {
				this.coupons.remove(coup);
				break;
			}
		}
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", customertName=" + customertName + ", pass=" + pass + ", coupons=" + coupons
				+ "]";
	}

	
	
	
}