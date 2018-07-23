package com.example.demo.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Entity(name = "COMPANY")
public class Company {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (name = "COMPANY_NAME")
	private String companyName;
	
	@Column (name = "PASSWORD")
	private String pass;
	
	@Column (name = "EMAIL")
	private String email;
	
	


	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH , CascadeType.REMOVE})
	@JoinColumn(name = "company_id")
	@JsonIgnore
	private List<Coupon> coupons;
	
	public Company(){}

	/**
	 * 
	 * @param companyName
	 * @param password
	 * @param email
	 */
	public Company(String companyName, String password, String email) {
		super();
		this.companyName = companyName;
		this.pass = password;
		this.email = email;
	}
	
	/**
	 * @return the id
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
	 * @return companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return password
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param password to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return all coupons
	 */
	public Collection<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * @param  coupons to set
	 */
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 * 
	 * @param  add coupon 
	 */
	public void addCoupon(Coupon coupon) {
		this.coupons.add(coupon);
	}
	
	/**
	 * 
	 * @param remove coupon 
	 */
	public void removeCoupon(Coupon coupon) {
		this.coupons.remove(coupon);
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", pass=" + pass + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}


	
}