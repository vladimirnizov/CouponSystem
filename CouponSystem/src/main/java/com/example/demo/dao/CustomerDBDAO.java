package com.example.demo.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.Repo.CustomerRepo;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.enums.CouponType;
import com.example.demo.utilites.Connection;
import com.example.demo.utilites.ConnectionPool;


/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Component
public class CustomerDBDAO implements CustomerDAO{

	
	@Autowired
	CustomerRepo custRepo;
	
	private int id;
	/**
	 * 
	 * @param customer to create customer in DB
	 */
	@Override
	public synchronized void createCustomer(Customer customer) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		custRepo.save(customer);
		ConnectionPool.getInstance().returnConnection(connection);
	}
	/**
	 * 
	 * @param customer to remove customer from DB
	 */
	@Override
	public synchronized void removeCustomer(Customer customer) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		custRepo.delete(customer);
		ConnectionPool.getInstance().returnConnection(connection);
		
	}
	/**
	 * 
	 * @param customer to update customer in DB
	 */
	@Override
	public synchronized void updateCustomer(Customer customer) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		custRepo.save(customer);
		ConnectionPool.getInstance().returnConnection(connection);
	}
	/**
	 * 
	 * @param id to get customer by id
	 * @return Customer
	 */
	@Override
	public synchronized Customer getCustomer(int id) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		Customer c= custRepo.findOne(id);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @param customerName to get customer by name
	 * @return Customer
	 */
	@Override
	public synchronized Customer getCustomerByName(String customerName) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		Customer c =custRepo.getCustomerByName(customerName);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @return array of all customers
	 */
	@Override
	public synchronized ArrayList<Customer> getAllCustomers() {
		Connection connection = ConnectionPool.getInstance().getConnection();
		ArrayList<Customer> c = (ArrayList<Customer>) custRepo.findAll();
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @return array of coupons
	 */
	@Override
	public synchronized ArrayList<Coupon> getCoupons() {
		Connection connection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> c= (ArrayList<Coupon>) custRepo.getAllPurchasedCouponByCustomer(this.id);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @param customerName to check customer
	 * @param password to login to customerName
	 * @return true if password correct, and false if incorrect
	 */
	@Override
	public synchronized boolean login(String customerName, String password) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		Customer cust = custRepo.getCustomerByName(customerName);
		ConnectionPool.getInstance().returnConnection(connection);
		if(cust == null)
		{
			System.out.println("Customer " + customerName+ " is not exsist");
			return false;
		}
		if(cust.getPass().equals(password)){
			this.id = cust.getId();
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return current customer
	 */
	@Override
	public synchronized Customer getLoginCustomer() {
		return this.getCustomer(this.id);
	}
	/**
	 * 
	 * @param type to get coupons by type
	 * @return array of coupons
	 */
	@Override
	public synchronized ArrayList<Coupon> getPurchaseCouponsByType(CouponType type) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> c= (ArrayList<Coupon>) custRepo.getAllPurchasedCouponByType(this.id, type);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @param id to get coupon by id
	 * @return coupon
	 */
	@Override
	public synchronized Coupon getPurchasedCoupon(int coup_id) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		Coupon c =custRepo.getPurchasedCouponByCustomer(this.id, coup_id);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}
	/**
	 * 
	 * @param price to get coupons by price
	 * @return array of coupons
	 */
	@Override
	public synchronized ArrayList<Coupon> getPurchaseCouponsByPrice(double price) {
		Connection connection = ConnectionPool.getInstance().getConnection();
		ArrayList<Coupon> c= (ArrayList<Coupon>) custRepo.getAllPurchasedCouponByPrice(this.id, price);
		ConnectionPool.getInstance().returnConnection(connection);
		return c;
	}


	
}