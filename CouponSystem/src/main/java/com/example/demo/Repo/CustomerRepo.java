package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.enums.CouponType;




/**
 * 
 * @author vladimirnizovtsev
 *
 */
public interface CustomerRepo extends CrudRepository<Customer, Integer> {

	
	/**
	 * 
	 * @param customertName to get customer by name
	 * @return Customer
	 */
	@Query("SELECT c FROM CUSTOMER c where c.customertName = :customertName")
	Customer getCustomerByName(@Param("customertName") String customertName);
	/**
	 * 
	 * @param cust_id
	 * @param coup_id
	 * getting coupon by id of selected customer
	 * @return Coupon
	 */
	@Query("SELECT COUP FROM COUPON AS COUP INNER JOIN COUP.customers AS CUST WHERE CUST.id = :cust_id AND COUP.id = :coup_id")
	Coupon getPurchasedCouponByCustomer(@Param("cust_id")int cust_id,@Param("coup_id")int coup_id);
/**
 * 
 * @param cust_id
 * @return array of coupons by selected customer
 */
	@Query("SELECT COUP FROM CUSTOMER AS CUST INNER JOIN CUST.coupons AS COUP WHERE CUST.id = :cust_id")
	List<Coupon> getAllPurchasedCouponByCustomer(@Param("cust_id")int cust_id);

	/**
	 * 
	 * @param cust_id
	 * @param type
	 * @return array of coupons of customer by selected type
	 */
	@Query("SELECT COUP FROM CUSTOMER AS CUST INNER JOIN CUST.coupons AS COUP WHERE CUST.id = :cust_id AND COUP.type = :type")
	List<Coupon> getAllPurchasedCouponByType(@Param("cust_id")int cust_id,@Param("type") CouponType type);

	/**
	 * 
	 * @param cust_id
	 * @param price
	 * @return array of coupons of customer by selected price
	 */
	@Query("SELECT COUP FROM CUSTOMER AS CUST INNER JOIN CUST.coupons AS COUP WHERE CUST.id = :cust_id AND COUP.price <= :price")
	List<Coupon> getAllPurchasedCouponByPrice(@Param("cust_id")int cust_id,@Param("price")double price);
	/**
	 * 
	 * @param id to get customer by id
	 * @return Customer
	 */
	@Query("SELECT c FROM CUSTOMER c WHERE c.id = :id")
	Customer findOne(@Param("id")int id);
}