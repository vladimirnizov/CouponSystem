package com.example.demo.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Repo.CouponRepo;
import com.example.demo.beans.Coupon;
import com.example.demo.enums.CouponType;
import com.example.demo.utilites.Connection;
import com.example.demo.utilites.ConnectionPool;


/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Component
public class CouponDBDAO implements CouponDAO {

	public CouponDBDAO() {
	
	}

	@Autowired
	CouponRepo coupRepo;
	
	/**
	 * 
	 * @param coup to create Coupon in DB
	 */
	@Override
	public synchronized void createCoupon(Coupon coup) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		coupRepo.save(coup);
		ConnectionPool.getInstance().returnConnection(connection);
	}
	/**
	 * 
	 * @param coup to remove Coupon from DB
	 */
	@Override
	public synchronized void removeCoupon(Coupon coup) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		coupRepo.removeCoupon(coup.getId());
		ConnectionPool.getInstance().returnConnection(connection);
	}
	/**
	 * 
	 * @param coup to update coupon in DB
	 */
	@Override
	public synchronized void updateCoupon(Coupon coup) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		coupRepo.save(coup);
		ConnectionPool.getInstance().returnConnection(connection);
	}
	/**
	 * 
	 * @param id to get coupon by id
	 * @return Coupon
	 */
	@Override
	public synchronized Coupon getCoupon(int id) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		Coupon c= coupRepo.findOne(id);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @return array of all coupons
	 */
	@Override
	public synchronized ArrayList<Coupon> getCoupons() {
		Connection connection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> c = (ArrayList<Coupon>) coupRepo.findAll();
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @param type to get coupons by type
	 * @return array of coupons
	 */ 
	@Override
	public synchronized ArrayList<Coupon> getCouponsByType(CouponType type) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> c = (ArrayList<Coupon>) coupRepo.findCouponByType(type);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}

}