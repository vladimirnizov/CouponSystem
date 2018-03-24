package com.example.demo.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Repo.CompanyRepo;
import com.example.demo.beans.Company;
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
public class CompanyDBDAO implements CompanyDAO {
	
	@Autowired
	private CompanyRepo compRepo;

	private int id;
	/**
	 * 
	 * @param comp to create Company in DB
	 */
	@Override
	public synchronized void createCompany(Company comp) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		compRepo.save(comp);
		ConnectionPool.getInstance().returnConnection(connection);
	}
	/**
	 * 
	 * @param comp to remove Company from DB
	 */
	@Override
	public synchronized void removeCompany(Company comp) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		compRepo.delete(comp);
		ConnectionPool.getInstance().returnConnection(connection);
		
	}
	/**
	 * 
	 * @param comp to update Company in DB
	 */
	@Override
	public synchronized void updateCompany(Company comp) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		compRepo.save(comp);
		ConnectionPool.getInstance().returnConnection(connection);
		
	}
	/**
	 * 
	 * @param id to get Company by id
	 * @return Company
	 */
	@Override
	public synchronized Company getCompany(int id) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		Company c = compRepo.findOne(id);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @param companyName to get Company by name
	 * @return Company
	 */
	public synchronized Company getCompanyByName(String companyName) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		Company c = compRepo.findCompByName(companyName);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @return array of all Companies
	 */
	@Override
	public synchronized ArrayList<Company> getAllCompanies() {
		Connection connection = ConnectionPool.getInstance().getConnection();
		ArrayList<Company> c = (ArrayList<Company>) compRepo.findAll();
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
		ArrayList<Coupon> c = (ArrayList<Coupon>) compRepo.getAllCoupons(this.id);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}

	/**
	 * 
	 * @param compName to check company by name
	 * @param password to login to compName
	 * @return true if password correct, and false if incorrect
	 */
	@Override
	public synchronized boolean login(String companyName, String password) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		Company comp = compRepo.findCompByName(companyName);
		ConnectionPool.getInstance().returnConnection(connection);
		if(comp == null)
		{
			System.out.println("Company" + companyName+ " is not exsist");
			return false;
		}
		if(comp.getPass().equals(password)){
			this.id = comp.getId();
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return the current company
	 */
	@Override
	public Company loggedCompany() {
		return getCompany(id);
	}
	/**
	 * 
	 * @param title to get coupon by title
	 * @return Coupon
	 */
	@Override
	public synchronized Coupon getCouponByTitle(String title) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		Coupon c= compRepo.getCouponByTitle(title, this.id);
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
		ArrayList<Coupon> c= (ArrayList<Coupon>) compRepo.getAllCouponsByType(this.id, type);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @param price to get coupons by price
	 * @return array of coupons
	 */
	@Override
	public synchronized ArrayList<Coupon> getCouponsByPrice(double price) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> c= (ArrayList<Coupon>) compRepo.getAllCouponsByPrice(this.id, price);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}



	
	
}