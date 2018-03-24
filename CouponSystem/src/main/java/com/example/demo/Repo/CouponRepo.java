package com.example.demo.Repo;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.beans.Coupon;
import com.example.demo.enums.CouponType;

/**
 * 
 * @author vladimirnizovtsev
 *
 */
public interface CouponRepo extends CrudRepository<Coupon, Integer> {

	/**
	 * 
	 * @param type to get coupons by type
	 * @return array of coupons
	 */
	@Query("SELECT c FROM COUPON c WHERE c.type = :type")
	List<Coupon> findCouponByType(@Param("type")CouponType type);
	

	
	/**
	 * 
	 * @param expiredDate
	 * @return array list of expired coupons
	 */
	@Query("SELECT c FROM COUPON c WHERE c.endDate < :expiredDate")
	List<Coupon> ExpiredCoupons(@Param("expiredDate")Date expiredDate);
	
	/**
	 * 
	 * @param id to delete coupon from DB by id
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM COUPON c WHERE c.id = :couponId")
	void removeCoupon(@Param("couponId") int couponId);
	
	/**
	 * 
	 * @param id to get coupon by id
	 * @return coupon
	 */
	@Query("SELECT c FROM COUPON c WHERE c.id = :id")
	Coupon findOne(@Param("id")int id);
	
}
