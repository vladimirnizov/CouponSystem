package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.enums.CouponType;


/**
 * 
 * @author vladimirnizovtsev
 *
 */
public interface CompanyRepo extends CrudRepository<Company, Integer> {
	
	

	
	/**
	 * 
	 * @param companyName to get company by name from DB
	 * @return Company
	 */
	@Query("SELECT c FROM COMPANY c WHERE c.companyName = :companyName")
	Company findCompByName(@Param("companyName")String companyName);
	/**
	 * 
	 * @param id to get company by id from DB
	 * @return
	 */
	@Query("SELECT c FROM COMPANY c WHERE c.id = :id")
	Company findOne(@Param("id")int id);
	/**
	 * 
	 * @param title
	 * @param comp_id
	 * getting coupon of selected company by title 
	 * @return Coupon
	 */
	@Query("SELECT COUP FROM COMPANY AS COMP INNER JOIN COMP.coupons AS COUP WHERE COMP.id = :comp_id AND COUP.title = :title")
	Coupon getCouponByTitle(@Param("title")String title,@Param("comp_id")int comp_id);

	/**
	 * 
	 * @param comp_id
	 * getting all coupons of selected company
	 * @return array of coupons
	 */
	@Query("SELECT COUP FROM COMPANY AS COMP INNER JOIN COMP.coupons AS COUP WHERE COMP.id = :comp_id")
	List<Coupon> getAllCoupons(@Param("comp_id")int comp_id);
	/**
	 * 
	 * @param comp_id
	 * @param type
	 * getting coupon of selected company by type
	 * @return array of coupons
	 */
	@Query("SELECT COUP FROM COMPANY COMP INNER JOIN COMP.coupons AS COUP WHERE COMP.id = :comp_id AND COUP.type = :type")
	List<Coupon> getAllCouponsByType(@Param("comp_id")int comp_id,@Param("type")CouponType type);
	
	/**
	 * 
	 * @param comp_id
	 * @param price
	 * getting coupon of selected company by price
	 * @return array of coupons
	 */
	@Query("SELECT COUP FROM COMPANY COMP INNER JOIN COMP.coupons AS COUP WHERE COMP.id = :comp_id AND COUP.price <= :price")
	List<Coupon> getAllCouponsByPrice(@Param("comp_id")int comp_id,@Param("price")double price);
	

}