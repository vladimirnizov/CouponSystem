package com.example.demo.facades;

import com.example.demo.enums.ClientType;
/**
 * 
 * @author vladimirnizovtsev
 *
 */
public interface CouponClientFacade {

	/**
	 * 
	 * @param userName 
	 * @param password 
	 * @return CouponClientFacade
	 */
	CouponClientFacade login(String userName,String password, ClientType type);
}