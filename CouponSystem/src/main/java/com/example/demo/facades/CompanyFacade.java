package com.example.demo.facades;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.dao.CompanyDBDAO;
import com.example.demo.dao.CouponDBDAO;
import com.example.demo.enums.ClientType;
import com.example.demo.enums.CouponType;
import com.example.demo.exceptions.AlreadyExist;
import com.example.demo.exceptions.NotExist;


/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Component
public class CompanyFacade implements CouponClientFacade{

	
	@Autowired
	private CompanyDBDAO compDBDAO;
	
	@Autowired
	private CouponDBDAO coupDBDAO;
	
	public CompanyFacade() {}
	
	/**
	 * 
	 * @param userName 
	 * @param password 
	 * @return CouponClientFacade
	 */
	@Override
	public CompanyFacade login(String userName, String password, ClientType type) {
		
		if(!type.equals(ClientType.COMPANY))
		{
			System.out.println(type+ " is wrong type.");
			return null;
		}
		
		if(compDBDAO.login(userName, password))
			return this;
		return null;
	}

	/**
	 * 
	 * @param coup to create coupon
	 * @throws AlreadyExist exception
	 */
	public synchronized void createCoupon(Coupon coup) {
		
		if(this.getCouponByTitle(coup.getTitle())!=null) {
			throw new AlreadyExist(coup.getTitle() + " already exist");
		}else {
			Company company = compDBDAO.loggedCompany();
			coupDBDAO.createCoupon(coup);
			company.addCoupon(coup);
			compDBDAO.updateCompany(company);
		}
	}

	/**
	 * 
	 * @param coupon to remove coupon
	 * @throws NotExist exception
	 */
	public synchronized void removeCoupon(Coupon coupon) {
		Coupon currentCoupon  = coupDBDAO.getCoupon(coupon.getId());
		if(currentCoupon == null) 
			throw new NotExist(coupon.getTitle() + " does not exist!");
		else{
			Company company = compDBDAO.loggedCompany();
			company.removeCoupon(currentCoupon);
			compDBDAO.updateCompany(company);
			coupDBDAO.removeCoupon(currentCoupon);
			}
	}

	/**
	 * 
	 * @param coupon to update coupon
	 * @throws NotExist exception
	 */
	public synchronized void updateCoupon(Coupon coupon) {
		Coupon currentCoupon  = coupDBDAO.getCoupon(coupon.getId());
		if(currentCoupon == null) 
			throw new NotExist(coupon.getTitle() + " does not exist!");
		else {
			currentCoupon.setAmount(coupon.getAmount());
			currentCoupon.setMessage(coupon.getMessage());
			currentCoupon.setImage(coupon.getImage());
			currentCoupon.setPrice(coupon.getPrice());
			currentCoupon.setEndDate(coupon.getEndDate());
		    coupDBDAO.updateCoupon(currentCoupon);
		}
	}

	/**
	 * 
	 * @param id to get coupon by id
	 * @return Coupon
	 */
	public Coupon getCoupon(int id) {
		return coupDBDAO.getCoupon(id);
	}
	
	/**
	 * 
	 * @param title to get coupon by title
	 * @return Coupon
	 */
	public Coupon getCouponByTitle(String title) {
		return compDBDAO.getCouponByTitle(title);
	}
	
	/**
	 * 
	 * @return array of all coupons
	 */
	public ArrayList<Coupon> getAllCoupons() {
		return compDBDAO.getCoupons();
	}

	/**
	 * 
	 * @param type to get coupons by type
	 * @return array of coupons
	 */
	public ArrayList<Coupon> getCouponsByType(CouponType type) {
		return compDBDAO.getCouponsByType(type);
	}

	/**
	 * 
	 * @param price to get coupons by price
	 * @return array of coupons
	 */
	public ArrayList<Coupon> getCouponsByPrice(double price) {
		return compDBDAO.getCouponsByPrice(price);
	}

	



}
